package com.CIMthetics.JVulkanExamples;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.Wayland.WaylandRegistryEntry;
import com.CIMthetics.jvulkan.Wayland.WlRegistryListener;
import com.CIMthetics.jvulkan.Wayland.Handles.WlRegistry;

public class MyRegistryListener implements WlRegistryListener
{
    private Logger log = LoggerFactory.getLogger("HWJVI_Client");;
    private ConcurrentHashMap<Integer,WaylandRegistryEntry> registryEntries = new ConcurrentHashMap<Integer,WaylandRegistryEntry>(40, 0.75F, 1);
    
    @Override
    public void registryAddEventHandler(Object userData, WlRegistry registry,
            int registryObjectId, String interfaceTextId, int registryObjectVersion)
    {
        log.debug("Registry Add Event {} interface {} ID {} version {}", registry.toString(), interfaceTextId, registryObjectId, registryObjectVersion);
        
        WaylandRegistryEntry newEntry = new WaylandRegistryEntry(registryObjectId, interfaceTextId, registryObjectVersion);
        registryEntries.put(Integer.valueOf(registryObjectId), newEntry);
    }

    @Override
    public void registryRemoveEventHandler(Object userData, WlRegistry registry,
            int registryObjectId)
    {
        log.debug("Registry Remove Event object {} no longer available.", registryObjectId);
        registryEntries.remove(Integer.valueOf(registryObjectId));
    }

    public LinkedList<WaylandRegistryEntry> getRegistryEntriesFor(String registryObjectName)
    {
        LinkedList<WaylandRegistryEntry> result = new LinkedList<WaylandRegistryEntry>();
        
        for (WaylandRegistryEntry entry : registryEntries.values() )
        {
            if (entry.getObjectName().equals(registryObjectName) == true)
            {
                result.add(entry);
            }
        }
        
        return result;
    }
}
