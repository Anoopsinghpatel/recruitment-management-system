package com.example.resumeportal.service;

import com.example.resumeportal.entity.Profile;
import com.example.resumeportal.entity.User;
import com.example.resumeportal.repository.ProfileRepository;
import com.example.resumeportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

@Service
public class ResumeService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${file.upload-dir}") private String uploadDir;
    @Value("${resume.parser.url}") private String parserUrl;
    @Value("${resume.parser.apikey}") private String parserKey;

    public ResumeService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile uploadAndParse(User user, MultipartFile file) throws Exception {
        // save file locally
        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);
        Path out = dir.resolve(System.currentTimeMillis() + "_" + file.getOriginalFilename());
        Files.write(out, file.getBytes());

        // call parser
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("apikey", parserKey);
        HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);
        ResponseEntity<String> resp = restTemplate.exchange(parserUrl, HttpMethod.POST, entity, String.class);

        String body = resp.getBody();
        JSONObject json = new JSONObject(body);

        Profile profile = user.getProfile();
        if (profile == null) profile = new Profile();
        profile.setApplicant(user);
        profile.setResumeFilePath(out.toString());
        profile.setName(json.optString("name", user.getName()));
        profile.setEmail(json.optString("email", user.getEmail()));
        profile.setPhone(json.optString("phone", ""));

        if (json.has("skills")) {
            JSONArray arr = json.getJSONArray("skills");
            profile.setSkills(arr.toString());
        }
        if (json.has("education")) {
            profile.setEducation(json.getJSONArray("education").toString());
        }

        if (json.has("experience")) {
            profile.setExperience(json.getJSONArray("experience").toString());
        }

        profile = profileRepository.save(profile);
        // attach to user and save
        user.setProfile(profile);
        userRepository.save(user);
        return profile;
    }
}
