package com.bookstoreapi.bookstoreapi.exceptions;

public class NoSuchBookExist extends RuntimeException{
    public NoSuchBookExist() {}
    public NoSuchBookExist(String name) {super(name);}
}
