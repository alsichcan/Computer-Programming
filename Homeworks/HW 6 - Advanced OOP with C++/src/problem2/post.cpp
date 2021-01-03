//
// Created by Park on 2020-12-18.
//
#include "post.h"

Post::Post(std::string title, std::string content): Post::Post(ID_NOT_INITIATED, DateTime::now(), title, content){}

Post::Post(int id, std::string formattedDateTime, std::string title, std::string content): id(id), dateTime(DateTime(formattedDateTime)), title(title), content(content){}


std::string Post::getSummary(){
    std::string summary = "";
    summary += ("id: " + std::to_string(id));
    summary += ", ";
    summary += ("created at: " + dateTime.getDateTime());
    summary += ", ";
    summary += ("title: " + title);

    return summary;
}


std::ostream& operator<<(std::ostream& os, const Post& post){
    return os << "-----------------------------------\n" << "id: " << post.id << "\n" << "created at: " << post.dateTime << "\n" << "title: " << post.title << "\n" << "content:\n" << post.content;
}

int Post::getId() { return id; }

void Post::setId(int id) { this->id = id; }

std::string Post::getDate() { return dateTime.getDateTime(); }

void Post::setDateTime(DateTime dateTime) { this->dateTime = dateTime; }

std::string Post::getTitle() { return title; }

std::string Post::getContent() { return content; }

void Post::setKeywordOccurrence(int keywordOccurrence) {
    this->keywordOccurrence = keywordOccurrence;
}

int Post::getKeywordOccurrence() {
    return keywordOccurrence;
}