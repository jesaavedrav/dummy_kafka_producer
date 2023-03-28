package com.js.kafkaclient.controllers

import com.js.kafkaclient.kafka.KafkaProducerClient
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/kafka-client/api"])
class KafkaController {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/event")
    fun applyCashOperation(@RequestBody body: Any, @RequestHeader("topic") topic: String): ResponseEntity<*> {
        log.info("Message Received...")
        val producer = KafkaProducerClient.getInstance()
        val future = producer.send(ProducerRecord(topic, System.currentTimeMillis().toString(), body))
        future.get()
        return ResponseEntity(body, HttpStatus.OK)
    }
}
