package com.example.myapplication;

public class Candidate {

    private String Name;
    private int constId;
    private String Constituency;
    private String Party;
    private String candidateId;

    public Candidate(){}

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public Candidate(String Name, int constId, String Constituency, String Party, String candidateId) {
        this.Name = Name;
        this.constId = constId;
        this.Constituency = Constituency;
        this.Party = Party;
        this.candidateId=candidateId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getConstId() {
        return constId;
    }

    public void setConstId(int constId) {
        this.constId = constId;
    }

    public String getConstituency() {
        return Constituency;
    }

    public void setConstituency(String constituency) {
        Constituency = constituency;
    }

    public String getParty() {
        return Party;
    }

    public void setParty(String party) {
        Party = party;
    }
}
