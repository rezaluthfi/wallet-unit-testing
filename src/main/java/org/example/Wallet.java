package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private String owner;
    private List<String> cards;
    private int cashNotes;
    private int coins;

    public Wallet() {
        this.cards = new ArrayList<>();
        this.cashNotes = 0;
        this.coins = 0;
    }

    //Fungsi untuk mengatur owner
    public void setOwner(String owner) {
        this.owner = owner;
    }

    //Fungsi untuk menambahkan kartu ke dalam wallet
    public void addCard(String card) {
        this.cards.add(card);
    }

    //Fungsi untuk mengambil kartu berdasarkan indeks
    public String getCard(int index) {
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        } else {
            return null;
        }
    }

    //Fungsi untuk menambahkan uang lembaran dengan validasi input negatif
    public void addCashNotes(int amount) {
        if (amount > 0) {
            this.cashNotes += amount;
        }
    }

    //Fungsi untuk menambahkan uang koin dengan validasi input negatif
    public void addCoins(int amount) {
        if (amount > 0) {
            this.coins += amount;
        }
    }

    //Fungsi untuk menarik uang lembaran dengan validasi input negatif
    public boolean withdrawCashNotes(int amount) {
        if (amount > 0 && this.cashNotes >= amount) {
            this.cashNotes -= amount;
            return true;
        }
        return false;
    }

    //Fungsi untuk menarik uang koin dengan validasi input negatif
    public boolean withdrawCoins(int amount) {
        if (amount > 0 && this.coins >= amount) {
            this.coins -= amount;
            return true;
        }
        return false;
    }

    //Fungsi untuk menampilkan jumlah total uang
    public int getTotalCash() {
        return this.cashNotes + this.coins;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getCards() {
        return cards;
    }
}
