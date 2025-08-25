package com.learning.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProducer {
	

//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public void sendUserEnrolledEvent(Long userId, Long courseId) {
//        Enrollment enrollment = new Enrollment(userId, courseId, "User enrolled in course");
//        kafkaTemplate.send("user.enrolled", enrollment);
//    }

}
