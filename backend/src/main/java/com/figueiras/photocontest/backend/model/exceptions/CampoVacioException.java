package com.figueiras.photocontest.backend.model.exceptions;

public class CampoVacioException extends InstanceException {

    public CampoVacioException(String name, String key){ super(name, key); }
}
