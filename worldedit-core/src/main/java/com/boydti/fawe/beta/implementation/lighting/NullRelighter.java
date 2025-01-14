package com.boydti.fawe.beta.implementation.lighting;

import java.util.concurrent.locks.ReentrantLock;

public class NullRelighter implements Relighter {

    public static NullRelighter INSTANCE = new NullRelighter();

    private NullRelighter() {
    }

    @Override
    public boolean addChunk(int cx, int cz, byte[] fix, int bitmask) {
        return false;
    }

    @Override
    public void addLightUpdate(int x, int y, int z) {

    }

    @Override
    public void fixLightingSafe(boolean sky) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void removeLighting() {

    }

    @Override
    public void fixBlockLighting() {

    }

    @Override
    public void fixSkyLighting() {

    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ReentrantLock getLock() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void close() throws Exception {

    }
}
