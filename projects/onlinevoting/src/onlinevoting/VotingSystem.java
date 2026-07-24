package onlinevoting;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class VotingSystem {
    private Map<String, User> users;
    private Map<String, Boolean> hasVoted;
    private Map<String, Integer> voteCount;
    private List<Candidate> candidates;
    private User currentUser;
    public VotingSystem() {
        users = new HashMap<>();
        hasVoted = new HashMap<>();
        voteCount = new HashMap<>();
        candidates = new ArrayList<>();
        currentUser = null;
        addCandidate(new Candidate("Adapa Vamshi", "Computer Science"));
        addCandidate(new Candidate("Rohith🤮", "Electronics"));
        addCandidate(new Candidate("Gayathri", "Mechanical"));
        addCandidate(new Candidate("krishna", "Mechanical"));
    }
    public void register(String username, String useridno, String password) throws Exception {
        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username cannot be empty.");
        }
        if (!useridno.matches("^(CSE|ECE|EEE|MEC|CIV|IT|AIML|AIDS|CSM|CSC)\\d{3}$")) {
            throw new Exception("Invalid User ID.");
        }
        if (password == null || password.length() < 4) {
            throw new Exception("Password must be at least 4 characters.");
        }
        if (users.containsKey(username)) {
            throw new Exception("Username already exists. Choose another name.");
        }
        users.put(username, new User(username, useridno, password));
        hasVoted.put(username, false);
    }
    public void login(String username,String useridno, String password) throws Exception {
        if (!users.containsKey(username)) {
            throw new Exception("User not found. Please register first.");
        }
        User user = users.get(username);
        if (!user.getPassword().equals(password)) {
            throw new Exception("Wrong password. Try again.");
        }
        currentUser = user;
    }
    public void logout() {
        currentUser = null;
    }
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    public String getCurrentUsername() {
        return currentUser != null ? currentUser.getUsername() : "";
    }
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
        voteCount.put(candidate.getName(), 0);
    }
    public void showCandidates() {
        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }
        System.out.println("\n--- Candidate List ---");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i));
        }
    }
    public void castVote(int candidateIndex) throws Exception {
        if (currentUser == null) {
            throw new Exception("Please login first before voting.");
        }
        String username = currentUser.getUsername();
        if (hasVoted.get(username)) {
            throw new Exception("You have already voted. One vote per student!");
        }
        if (candidateIndex < 1 || candidateIndex > candidates.size()) {
            throw new Exception("Invalid candidate number.");
        }
        Candidate chosen = candidates.get(candidateIndex - 1);
        String candidateName = chosen.getName();
        voteCount.put(candidateName, voteCount.get(candidateName) + 1);
        hasVoted.put(username, true);
        System.out.println("Vote recorded for: " + candidateName);
    }
    public void showResults() {
        if (voteCount.isEmpty()) {
            System.out.println("No votes cast yet.");
            return;
        }
        System.out.println("\n========== ELECTION RESULTS ==========");
        String winner = null;
        int maxVotes = -1;
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            String name = entry.getKey();
            int votes = entry.getValue();
            System.out.println(name + " : " + votes + " vote(s)");
            if (votes > maxVotes) {
                maxVotes = votes;
                winner = name;
            }
        }
        System.out.println("--------------------------------------");
        if (maxVotes == 0) {
            System.out.println("No winner yet. No votes have been cast.");
        } else {
            System.out.println("WINNER (Finalist): " + winner + " with " + maxVotes + " vote(s)");
        }
        System.out.println("=======================\n");
    }
}