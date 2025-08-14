package com.back.domain.post.post.service

import com.back.domain.member.member.entity.Member
import com.back.domain.post.post.entity.Post
import com.back.domain.post.post.repository.PostRepository
import com.back.domain.post.postComment.entity.PostComment
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@RequiredArgsConstructor
class PostService(
        private val postRepository: PostRepository
) {

    fun count(): Long {
        return postRepository.count()
    }

    @Transactional
    fun write(author: Member, title: String, content: String): Post {
        val post = Post(author, title, content)
        return postRepository.save(post)
    }

    @Transactional(readOnly = true)
    fun findById(id: Int): Optional<Post> {
        return postRepository.findById(id)
    }

    @Transactional(readOnly = true)
    fun findAll(): MutableList<Post> {
        return postRepository.findAll()
    }

    @Transactional
    fun modify(post: Post, title: String, content: String) {
        post.modify(title, content)
    }

    @Transactional
    fun writeComment(author: Member, post: Post, content: String): PostComment {
        return post.addComment(author, content)
    }

    @Transactional
    fun deleteComment(post: Post, postComment: PostComment): Boolean {
        return post.deleteComment(postComment)
    }

    @Transactional
    fun modifyComment(postComment: PostComment, content: String) {
        postComment.modify(content)
    }

    @Transactional
    fun delete(post: Post) {
        postRepository.delete(post)
    }

    @Transactional(readOnly = true)
    fun findLatest(): Optional<Post> {
        return postRepository.findFirstByOrderByIdDesc()
    }

    fun flush() {
        postRepository.flush()
    }
}
