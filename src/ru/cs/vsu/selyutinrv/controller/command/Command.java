package ru.cs.vsu.selyutinrv.controller.command;

public interface Command {
    void execute();
    String getDescription();
}