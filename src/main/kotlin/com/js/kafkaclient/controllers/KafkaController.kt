package com.js.kafkaclient.controllers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.js.kafkaclient.kafka.KafkaProducerClient
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/kafka-client/api"])
class KafkaController {
    private val log = LoggerFactory.getLogger(javaClass)
    @PostMapping("/event")
    fun applyCashOperation(@RequestBody body: Any): ResponseEntity<*> {
        log.info("Message Received...")
        val producer = KafkaProducerClient.getInstance()
        val future = producer.send(ProducerRecord("stores-input", System.currentTimeMillis().toString(), body))
        future.get()
        return ResponseEntity(body, HttpStatus.OK)
    }
}

class KafkaMessageSerializer : Serializer<Any> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: Any?): ByteArray? {
        log.info("Serializing...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Product to ByteArray[]")
        )
    }

    override fun close() {}
}