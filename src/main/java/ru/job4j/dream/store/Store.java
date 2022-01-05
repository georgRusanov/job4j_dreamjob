package ru.job4j.dream.store;

import java.util.Map;
import java.util.Optional;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.Collection;
import ru.job4j.dream.model.User;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void savePost(Post post);

    void saveCandidate(Candidate candidate);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    void deleteCandidate(int id);

    void saveUser(User user);

    User findUserByEmail(String email);
}