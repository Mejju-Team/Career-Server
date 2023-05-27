package com.example.career.domain.meeting.service;

import com.example.career.domain.meeting.dto.ZoomMeetingObjectDTO;
import com.example.career.domain.meeting.dto.ZoomMeetingSettingsDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.Date;
import java.util.UUID;



@Service
public class ZoomMeetingServiceImpl {
    public ZoomMeetingObjectDTO createMeeting() {
        ZoomMeetingObjectDTO zoomMeetingObjectDTO = new ZoomMeetingObjectDTO();
        System.out.println("Request to create a Zoom meeting");
        // replace zoomUserId with your user ID
        String apiUrl = "https://api.zoom.us/v2/users/" + "jongminshin373@gmail.com" + "/meetings";

        // replace with your password or method
//        zoomMeetingObjectDTO.setPassword("dkdrlahEl13!");
        // replace email with your email
        zoomMeetingObjectDTO.setHost_email("jongminshin373@gmail.com");

        // Optional Settings for host and participant related options
        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
        settingsDTO.setJoin_before_host(false);
        settingsDTO.setParticipant_video(true);
        settingsDTO.setHost_video(false);
        settingsDTO.setAuto_recording("cloud");
        settingsDTO.setMute_upon_entry(true);
        zoomMeetingObjectDTO.setSettings(settingsDTO);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "eyJzdiI6IjAwMDAwMSIsImFsZyI6IkhTNTEyIiwidiI6IjIuMCIsImtpZCI6ImY1ZTVjZjgxLTkwZWUtNDQwOS1iZjhjLTk2YWJhN2M3Y2Y0MiJ9.eyJ2ZXIiOjksImF1aWQiOiJhYzlhYWM0MjgxMGQ0M2ZiYzljMzQ0NDAxMDE5NmIzMyIsImNvZGUiOiJIdFJpWEJpUXNKZndrZzhvd0hQUy15bzRHbTZJYV9NSmciLCJpc3MiOiJ6bTpjaWQ6TW4za3JMekRUUnlZTnoycnMxaVJ2ZyIsImdubyI6MCwidHlwZSI6MCwidGlkIjowLCJhdWQiOiJodHRwczovL29hdXRoLnpvb20udXMiLCJ1aWQiOiJNdWxLOWNNRlJsU3FndmlmZ2lkS05RIiwibmJmIjoxNjg1MjA1NTk5LCJleHAiOjE2ODUyMDkxOTksImlhdCI6MTY4NTIwNTU5OSwiYWlkIjoiN1Z0dXNjUklUNEtZa0tWM2RNNHM3ZyJ9.36ZaZhX2WsGXDXoZAkRPHYYIzZrnQcN7Ypu2DeRwlf89Ux7qOJonCo3xM08QKnyhVCtwz0znoA3uLKOO_0ATVg");
        headers.add("content-type", "application/json");
        HttpEntity<ZoomMeetingObjectDTO> httpEntity = new HttpEntity<ZoomMeetingObjectDTO>(zoomMeetingObjectDTO, headers);
        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
        if(zEntity.getStatusCodeValue() == 201) {
            System.out.println("Zooom meeeting response {}" + zEntity);
            return zEntity.getBody();
        } else {
            System.out.println("Error while creating zoom meeting {}" + zEntity.getStatusCode());
        }
        return zoomMeetingObjectDTO;
    }


    /**
     * Generate JWT token for Zoom using api credentials
     *
     * @return JWT Token String
     */

}
