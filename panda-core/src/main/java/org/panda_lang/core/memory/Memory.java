package org.panda_lang.core.memory;

public interface Memory {

    /**
     * @param type name of segment type
     * @return type id of the created segment
     */
    int allocate(String type);

    /**
     * Allows you to allocate custom memory segment.
     * If you are not sure we strongly recommend use {@link #allocate(String)}
     *
     * @param memorySegment specified custom segment
     * @return segment id of the specified segment
     */
    int allocate(MemorySegment memorySegment);

    /**
     * @param typeID id of the type returned by {@link #allocate(String)}
     * @return memory segment associated with specified type id
     */
    MemorySegment get(int typeID);

}
