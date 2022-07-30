package com.example.mreview.global.entity;

import com.example.mreview.entity.QMember;
import com.example.mreview.entity.QMovie;

public enum SearchValue {
    TITLE("QMovie.movie.title");

    private String value;

    SearchValue(String value) {
        this.value = value;
    }
}
