package ru.vsu.cs.selyutinrv.controller.command;

public interface Command {
    void execute();
    String getDescription();
}