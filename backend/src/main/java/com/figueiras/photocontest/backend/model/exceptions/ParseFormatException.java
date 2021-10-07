package com.figueiras.photocontest.backend.model.exceptions;

public class ParseFormatException extends InstanceException{
    public ParseFormatException(Object key){super("La fecha no tiene un formato válido", key);}
}
