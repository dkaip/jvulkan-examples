package com.CIMthetics.JVulkanExamples;

import java.util.EnumSet;

import com.CIMthetics.jvulkan.VulkanCore.VK11.VkDebugUtilsMessengerCallbackEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugUtilsMessageSeverityFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugUtilsMessageTypeFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Structures.VkDebugUtilsMessengerCallbackDataEXT;

public class MyDebugUtilsMessengerCallbackEXT implements VkDebugUtilsMessengerCallbackEXT
{

    @Override
    public boolean invoke(VkDebugUtilsMessageSeverityFlagBitsEXT arg0,
                          EnumSet<VkDebugUtilsMessageTypeFlagBitsEXT> arg1,
                          VkDebugUtilsMessengerCallbackDataEXT arg2, Object arg3)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
