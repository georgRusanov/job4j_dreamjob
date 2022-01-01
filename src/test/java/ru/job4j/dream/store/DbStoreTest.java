package ru.job4j.dream.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import org.junit.Test;

public class DbStoreTest {
    private static final Store store = DbStore.instOf();
    @Test
    public void whenCreatePost() {
        Post post = new Post(0, "Java Job0");
        store.savePost(post);
        Post postInDb = store.findPostById(post.getId());
        assertEquals(postInDb.getName(), post.getName());
    }

    @Test
    public void whenCreateCandidate() {
        Candidate candidate = new Candidate(0, "Name0");
        store.saveCandidate(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertEquals(candidateInDb.getName(), candidate.getName());
    }

    @Test
    public void testInstOf() {
        Store store1 = DbStore.instOf();
        assertEquals(store1, store);
    }

    @Test
    public void testFindAllPosts() {
        Post post = new Post(2, "Java Job2");
        store.savePost(post);
        Post post2 = new Post(3, "Java Job3");
        store.savePost(post2);
        ArrayList<Post> posts = (ArrayList<Post>) store.findAllPosts();
        ArrayList<Post> expectedPosts = new ArrayList<>(List.of(post, post2));
        assertTrue(posts.containsAll(expectedPosts));
    }

    @Test
    public void testFindAllCandidates() {
        Candidate candidate = new Candidate(2, "Name2");
        store.saveCandidate(candidate);
        Candidate candidate2 = new Candidate(3, "Name3");
        store.saveCandidate(candidate2);
        ArrayList<Candidate> candidates =  (ArrayList<Candidate>) store.findAllCandidates();
        ArrayList<Candidate> expectedCandidates = new ArrayList<>(List.of(candidate, candidate2));
        assertTrue(candidates.containsAll(expectedCandidates));
    }

    @Test
    public void testDeleteCandidate() {
        Candidate candidate = new Candidate(5, "Name5");
        store.saveCandidate(candidate);
        store.deleteCandidate(candidate.getId());
        Candidate actual = store.findCandidateById(candidate.getId());
        assertNull(actual);

    }
}
