package com.sunul.sunul.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMbtiChat(
    @SerializedName("choices")
    val choices: List<Choice>,
    @SerializedName("created")
    val created: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("object")
    val `object`: String,
    @SerializedName("usage")
    val usage: Usage
)

@Serializable
data class Usage(
    @SerializedName("completion_tokens")
    val completion_tokens: Int,
    @SerializedName("prompt_tokens")
    val prompt_tokens: Int,
    @SerializedName("total_tokens")
    val total_tokens: Int
)

@Serializable
data class Message(
    @SerializedName("content")
    val content: String,
    @SerializedName("role")
    val role: String
)

@Serializable
data class Choice(
    @SerializedName("finish_reason")
    val finish_reason: String,
    @SerializedName("index")
    val index: Int,
    @SerializedName("message")
    val message: Message
)