/*
 * Copyright 2019 Douglas Kaip
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.CIMthetics.JVulkanExamples;

import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanConstants.VK_EXT_DEBUG_REPORT_EXTENSION_NAME;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanConstants.VK_KHR_SURFACE_EXTENSION_NAME;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanConstants.VK_KHR_SWAPCHAIN_EXTENSION_NAME;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanConstants.VK_KHR_WAYLAND_SURFACE_EXTENSION_NAME;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.VK_MAKE_VERSION;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.pushDataToVirtualMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkAcquireNextImageKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkAllocateCommandBuffers;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkAllocateDescriptorSets;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkAllocateMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkBeginCommandBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkBindBufferMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdBeginRenderPass;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdBindDescriptorSets;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdBindIndexBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdBindPipeline;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdBindVertexBuffers;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdCopyBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdDrawIndexed;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCmdEndRenderPass;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateCommandPool;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateDebugReportCallbackEXT;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateDescriptorPool;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateDescriptorSetLayout;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateDevice;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateFence;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateFramebuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateGraphicsPipelines;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateImageView;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateInstance;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreatePipelineLayout;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateRenderPass;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateSemaphore;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateShaderModule;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateSwapchainKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkCreateWaylandSurfaceKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyCommandPool;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyDebugReportCallbackEXT;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyDescriptorPool;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyDescriptorSetLayout;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyDevice;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyFence;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyFramebuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyImageView;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyInstance;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyPipeline;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyPipelineLayout;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyRenderPass;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroySemaphore;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroyShaderModule;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroySurfaceKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDestroySwapchainKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkDeviceWaitIdle;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkEndCommandBuffer;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkEnumerateDeviceExtensionProperties;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkEnumeratePhysicalDevices;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkFreeCommandBuffers;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkFreeMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetBufferMemoryRequirements;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetDeviceQueue;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceFeatures;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceMemoryProperties;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceProperties;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceQueueFamilyProperties;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceSurfaceCapabilitiesKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceSurfaceFormatsKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceSurfacePresentModesKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceSurfaceSupportKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetPhysicalDeviceWaylandPresentationSupportKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkGetSwapchainImagesKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkMapMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkQueuePresentKHR;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkQueueSubmit;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkQueueWaitIdle;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkResetFences;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkUnmapMemory;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkUpdateDescriptorSets;
import static com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions.vkWaitForFences;
import static com.CIMthetics.jvulkan.VulkanCore.VKUtil.vkResultToString;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanConstants;
import com.CIMthetics.jvulkan.VulkanCore.VK11.VulkanFunctions;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkAccessFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkAttachmentLoadOp;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkAttachmentStoreOp;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkBlendFactor;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkBlendOp;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkBufferUsageFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkColorComponentFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkCommandBufferLevel;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkCommandBufferUsageFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkComponentSwizzle;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkCompositeAlphaFlagBitsKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkCullModeFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkDescriptorType;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkDynamicState;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkFenceCreateFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkFormat;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkFrontFace;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkImageAspectFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkImageLayout;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkImageUsageFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkImageViewType;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkIndexType;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkInstanceCreateFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkLogicOp;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkMemoryMapFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkMemoryPropertyFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkPipelineBindPoint;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkPipelineStageFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkPolygonMode;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkPresentModeKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkPrimitiveTopology;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkQueueFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkResult;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkSampleCountFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkShaderStageFlagBits;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkSharingMode;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkSubpassContents;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Enums.VkVertexInputRate;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.MappedMemoryPointer;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkBuffer;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkCommandBuffer;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkCommandPool;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkDescriptorPool;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkDescriptorSet;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkDescriptorSetLayout;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkDevice;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkDeviceMemory;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkFence;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkFramebuffer;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkImage;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkImageView;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkInstance;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkPhysicalDevice;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkPipeline;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkPipelineCache;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkPipelineLayout;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkQueue;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkRenderPass;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkSemaphore;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkShaderModule;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Handles.VkSwapchainKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.IntReturnValue;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkAllocationCallbacks;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkAttachmentDescription;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkAttachmentReference;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkBufferCopy;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkClearColorValue;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkClearValue;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkComponentMapping;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkDescriptorBufferInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkDescriptorPoolSize;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkDescriptorSetLayoutBinding;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkExtensionProperties;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkExtent2D;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkImageSubresourceRange;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkMemoryRequirements;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkMemoryType;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkOffset2D;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkPhysicalDeviceFeatures;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkPhysicalDeviceMemoryProperties;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkPhysicalDeviceProperties;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkPipelineColorBlendAttachmentState;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkQueueFamilyProperties;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkRect2D;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkSubpassDependency;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkSubpassDescription;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkSurfaceCapabilitiesKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkSurfaceFormatKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkVertexInputAttributeDescription;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkVertexInputBindingDescription;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkViewport;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.VkWriteDescriptorSet;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkApplicationInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkBufferCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkCommandBufferAllocateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkCommandBufferBeginInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkCommandPoolCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkDescriptorPoolCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkDescriptorSetAllocateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkDescriptorSetLayoutCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkDeviceCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkDeviceQueueCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkFenceCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkFramebufferCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkGraphicsPipelineCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkImageViewCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkInstanceCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkMemoryAllocateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineColorBlendStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineDynamicStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineInputAssemblyStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineLayoutCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineMultisampleStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineRasterizationStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineVertexInputStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPipelineViewportStateCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkPresentInfoKHR;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkRenderPassBeginInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkRenderPassCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkSemaphoreCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkShaderModuleCreateInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkSubmitInfo;
import com.CIMthetics.jvulkan.VulkanCore.VK11.Structures.CreateInfos.VkSwapchainCreateInfoKHR;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkColorSpaceKHR;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugReportFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Handles.VkDebugReportCallbackEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Handles.VkSurfaceKHR;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Structures.CreateInfos.VkDebugReportCallbackCreateInfoEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Structures.CreateInfos.VkPipelineShaderStageCreateInfo;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Structures.CreateInfos.VkWaylandSurfaceCreateInfoKHR;
import com.CIMthetics.jvulkan.Wayland.Objects.WaylandGlobalRegistryEntry;
import com.CIMthetics.jvulkan.Wayland.Objects.WlCompositor;
import com.CIMthetics.jvulkan.Wayland.Objects.WlDisplaySingleton;
import com.CIMthetics.jvulkan.Wayland.Objects.WlRegistry;
import com.CIMthetics.jvulkan.Wayland.Objects.WlShell;
import com.CIMthetics.jvulkan.Wayland.Objects.WlShellSurface;
import com.CIMthetics.jvulkan.Wayland.Objects.WlSurface;

/**
 * This is the vulkan tutorial on Uniform Buffers.
 * 
 * @author Douglas Kaip
 *
 */
public class Test5
{
    private Logger log;

    private static final boolean validationDesired = Boolean.parseBoolean(System.getProperty("vulkan.validation", "false"));

    // Wayland items
    private WlDisplaySingleton  waylandDisplay;
    private WlRegistry          waylandRegistry;
    private WlCompositor        waylandCompositor;
    private WlShell             waylandShell;
    private WlSurface           waylandSurface;
    private WlShellSurface      waylandShellSurface;
    
    private int     windowWidth     = 1024;
    private int     windowHeight    = 768;
    
    
    private VkInstance          vulkanInstance;
    private VkPhysicalDevice    vulkanPhysicalDevice;
    private VkDevice            vulkanLogicalDevice;
    private VkQueue             vulkanGraphicsCommandsQueue;
    private VkSurfaceKHR        vulkanSurface;

    VkDebugReportCallbackEXT debugCallbackHandle;
    
    private static String[] validationLayers =
    {
        "VK_LAYER_LUNARG_standard_validation"
    };
    
    private int totalNumberOfQueueFamilies = -1;
    private int graphicsQueueFamilyIndex = -1;
    private int presentationQueueFamilyIndex = -1;
    
    private VkQueue                 vulkanPresentationCommandsQueue;

    VkPhysicalDeviceFeatures chosenPhysicalDeviceFeatures;
    private Collection<String> vulkanGraphicsDeviceExtensionNames = new ArrayList<String>();
    private Collection<String> vulkanGraphicsenabledLayerNames = new ArrayList<String>();

    private MyDebugCallback     myDebugCallback = new MyDebugCallback();
    
    private volatile VkSwapchainKHR vulkanSwapchainHandle;
    private VkRenderPass            vulkanRenderPassHandle;
    private VkPipelineLayout        vulkanPipelineLayoutHandle;
    private VkPipeline              vulkanPipelineHandle;
    private VkCommandPool           vulkanGraphicsCommandPoolHandle;



    private SwapchainSupportDetails     swapchainSupportDetails;
    private VkExtent2D                  swapchainExtentUsed;
    private VkFormat                    swapchainImageFormatUsed;
    private ArrayList<VkImage>          swapchainImageReferences = new ArrayList<VkImage>();
    private ArrayList<VkImageView>      swapchainImageViewReferences = new ArrayList<VkImageView>();
    private ArrayList<VkFramebuffer>    swapchainFramebufferReferences = new ArrayList<VkFramebuffer>();
    private ArrayList<VkCommandBuffer>  swapchainRenderCommandBuffers = new ArrayList<VkCommandBuffer>();
    
    private ArrayList<VkSemaphore>      imageAvailableSemaphoreHandles = new ArrayList<VkSemaphore>();
    private ArrayList<VkSemaphore>      renderFinishedSemaphoreHandles = new ArrayList<VkSemaphore>();
    private ArrayList<VkFence>          inFlightFenceHandles = new ArrayList<VkFence>();
    
    private int currentFrame = 0;
    
    private static final long       UINT64_MAX         = 0xFFFFFFFFFFFFFFFFL;
    private static final int        MAX_FRAMES_IN_FLIGHT = 3;

    private BufferInformation       vertexBufferInformation = null;
    private BufferInformation       indexBufferInformation = null;
    
    private ArrayList<BufferInformation> uniformBufferInformationCollection = new ArrayList<BufferInformation>();
    private VkDescriptorSetLayout       descriptorSetLayoutHandle;
    private VkDescriptorPool            descriptorPoolHandle;
    private ArrayList<VkDescriptorSet>  descriptorSetHandles = new ArrayList<VkDescriptorSet>();
    
    private String shaderPath = null;
    
    private Instant                 startTime = Instant.now();

    private class BufferInformation
    {
        VkBuffer        bufferHandle;
        VkDeviceMemory  bufferMemoryHandle;
    }
    
    private class Position
    {
        float x;
        float y;
        
        Position(float x, float y)
        {
            this.x = x;
            this.y = y;
        }
    }
    
    private class Color
    {
        float redComponent;
        float greenComponent;
        float blueComponent;
        
        Color(float red, float green, float blue)
        {
            redComponent = red;
            greenComponent = green;
            blueComponent = blue;
        }
    }
    
    private class UniformBufferObject
    {
        Matrix4f model;
        
        Matrix4f view;
        
        Matrix4f projection;
        
        public int sizeInBytes()
        {
            return ((4 * 4) * 4) * 3;
        }
    }
    
    private UniformBufferObject uniformBufferObject = new UniformBufferObject();
    
    private class Vertex
    {
        Position position;
        Color    color;
        
        Vertex(Position position, Color color)
        {
            this.position = position;
            this.color = color;
        }
    }
    
    private Vertex[] vertices;
    
    private int[] indices = new int[] {0, 1, 2, 0, 2, 3};

    public Test5()
    {
        log = LoggerFactory.getLogger("jvulkan-example");
        
        String architectureDataModel = System.getProperty("sun.arch.data.model");
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        
        // See what OS we are running under
        if (operatingSystem.equals("linux") == false)
        {
            log.error("At this point there is only a native image for Linux x86_64.");
            System.exit(-1);
        }
        
        // See if we are running 64 bit Java
        if (architectureDataModel.equals("64") == false)
        {
            log.error("At this point there is only a 64 bit native image Linux.");
            System.exit(-1);
        }
        
        String nativeLibraryPath = System.getProperty("jvulkan.native.library.path", "NotFound");
        if (nativeLibraryPath.equals("NotFound") == true)
        {
            log.error("A valid path to the native library must be specified with the jvulkan.native.library.path command line argument.");
            log.error("i.e. java -Djvulkan.native.library.path=\"/myprojects/mylibpath/\".");
            System.exit(-1);
        }
        
        shaderPath = System.getProperty("jvulkan-examples.shader.path", "NotFound");
        if (shaderPath.equals("NotFound") == true)
        {
            log.error("A valid path to the location of the shaders (.spv files) must be specified with the jvulkan-examples.shader.path command line argument.");
            log.error("i.e. java -Djvulkan-examples.shader.path=\"/myprojects/myshaders/\".");
            System.exit(-1);
        }

        if (shaderPath.endsWith("/") == false)
            shaderPath = shaderPath + "/";

        @SuppressWarnings("unused")
        VulkanFunctions vf = new VulkanFunctions(nativeLibraryPath, "libjvulkan-natives-Linux-x86_64.so");
        
        vertices = new Vertex[4];
        
        Color color;
        Position position;
        Vertex vertex;
        
        color = new Color(1.0f, 0.0f, 0.0f);
        position = new Position(-0.5f, -0.5f);
        vertex = new Vertex(position, color);
        vertices[0] = vertex;

        color = new Color(0.0f, 1.0f, 0.0f);
        position = new Position(0.5f, -0.5f);
        vertex = new Vertex(position, color);
        vertices[1] = vertex;

        color = new Color(0.0f, 0.0f, 1.0f);
        position = new Position(0.5f, 0.5f);
        vertex = new Vertex(position, color);
        vertices[2] = vertex;

        color = new Color(1.0f, 1.0f, 1.0f);
        position = new Position(-0.5f, 0.5f);
        vertex = new Vertex(position, color);
        vertices[3] = vertex;

    }
    

    public static void main(String[] args)
    {
        Test5 test = new Test5();
        
        test.init();
        
        test.mainLoop();
        
        test.cleanup();
    }

    private void init()
    {
//        initGLFWWindow();
        
        initWaylandWindow();
        initVulkan();
    }
    
    private void initWaylandWindow()
    {
        waylandDisplay = WlDisplaySingleton.getInstance();
        waylandDisplay.connect(null);
        
        waylandRegistry = waylandDisplay.getRegistry();
        
        waylandDisplay.sync();
        
        /*
         * We need a slight delay so that the registry may be populated.  It is
         * done asynchronously so if you just "rip" through the code you can get
         * to here and find that entries that are supposed to be in the registry
         * are not there yet.
         */
        try
        {
            Thread.sleep(1200);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        LinkedList<WaylandGlobalRegistryEntry>registryEntries = waylandRegistry.getRegistryEntriesFor("wl_compositor");
        if (registryEntries.size() != 1)
        {
            if (registryEntries.size() == 0)
            {
                log.error("Did not find the wl_compositor_interface in the registry.");
                System.exit(-1);
            }
            else if (registryEntries.size() > 1)
            {
                log.error("There was more than one wl_compositor_interface in the registry.");
                System.exit(-1);
            }
        }
        WaylandGlobalRegistryEntry compositorInterfaceEntry = registryEntries.get(0);
        /*
         * Note:
         * If we need a different interface version than is in the global
         * registry object list we will need to create the WaylandGlobalRegistryEntry
         * manually and specify the required verion.
         */
        waylandCompositor = (WlCompositor) waylandRegistry.bind(compositorInterfaceEntry);
        
        registryEntries = waylandRegistry.getRegistryEntriesFor("wl_shell");
        if (registryEntries.size() != 1)
        {
            // Houston we have a problem
            if (registryEntries.size() == 0)
            {
                log.error("Did not find the wl_shell_interface in the registry.");
                System.exit(-1);
            }
            else if (registryEntries.size() > 1)
            {
                log.error("There was more than one wl_shell_interface in the registry.");
                System.exit(-1);
            }
        }
        WaylandGlobalRegistryEntry shellInterfaceEntry = registryEntries.get(0);

        waylandShell = (WlShell)waylandRegistry.bind(shellInterfaceEntry);

        waylandSurface = waylandCompositor.createSurface();
        log.debug("Wayland surface {}", waylandSurface.toString());
        waylandShellSurface = waylandShell.getShellSurface(waylandSurface);
        waylandShellSurface.setTopLevel();
        
        waylandDisplay.sync();
    }
    
    private void cleanupWaylandWindow()
    {
        log.debug("Disconnecting from wayland display.");
        waylandDisplay.disconnect();
    }
    
    private void initVulkan()
    {
        // Vulkan initialization stuff here
        
        // First create a Vulkan instance
        createVulkanInstance();
        
        pickPhysicalDevice();
        
        boolean supported = vkGetPhysicalDeviceWaylandPresentationSupportKHR(vulkanPhysicalDevice, graphicsQueueFamilyIndex, waylandDisplay.getHandle());
        log.debug("Wayland presentaion support is {}", supported);
        
        /*
         * If this is successful graphicsQueueFamiliyIndex should be set with
         * the queue family that contains the graphics device
         * 
         * FIXME  this should be modified so that the pickPhysicalDevice
         * also uses this as a criteria for choosing a proper device.
         */
        findQueueFamilies();
        
        // This needs to be before createLogicalDevice
        setupDeviceExtensions();
        
        createLogicalDevice();
        
        createGraphicsCommandQueue();

        createWindowSurface();
        
        findPresentationQueueFamily();
        
        createPresentationCommandQueue();
        
        retrieveSwapchainSupportDetails();

        testSwapchainSuitability();
        
        createSwapchain();
        
        createImageViews();
        
        createRenderPass();
        
        createDescriptorSetLayout();
        
        createGraphicsPipeline();
        
        createFrameBuffers();

        createCommandPool();
        
        createVertexBuffer();
        
        createIndexBuffer();
        
        createUniformBuffers();
        
        createDescriptorPool();
        
        createDescriptorSets();

        createCommandBuffers();
        
        createSyncObjects();
    }
    
    private void createDescriptorSets()
    {
        /*
         * We are creating one descriptor set for each
         * swapchain image.  All of the sets have the 
         * same layout.
         */
        int setsNeeded = uniformBufferInformationCollection.size();
        
        log.trace("Creating {} descriptor sets.", setsNeeded);
        
        Collection<VkDescriptorSetLayout> descriptorSets = new LinkedList<VkDescriptorSetLayout>();
        for (int i = 0; i < setsNeeded; i++)
        {
            descriptorSets.add(descriptorSetLayoutHandle);
        }
        
        VkDescriptorSetAllocateInfo descriptorSetAllocateInfo = new VkDescriptorSetAllocateInfo();
        descriptorSetAllocateInfo.setDescriptorPool(descriptorPoolHandle);
        descriptorSetAllocateInfo.setSetLayouts(descriptorSets);
        
        /*
         *FIXME
         * Modify vkAllocateDescriptorSets so that it creates the descriptor set
         * handles as needed and adds them to the collection.  In this case
         * having to add empty handles to the collection first is icky.
         */
        for (int i = 0; i < setsNeeded; i++)
        {
            descriptorSetHandles.add(new VkDescriptorSet());
        }
        VkResult result = vkAllocateDescriptorSets(vulkanLogicalDevice, descriptorSetAllocateInfo, descriptorSetHandles);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("failed to allocate descriptor sets!: " + vkResultToString(result));
        }

        for (int i = 0; i < descriptorSetHandles.size(); i++)
        {
            VkDescriptorBufferInfo descriptorBufferInfo =
                    new VkDescriptorBufferInfo(
                            uniformBufferInformationCollection.get(i).bufferHandle,
                            0L,
                            192L); // FIXME magic number...sizeof UniformBufferObject
            
            Collection<VkDescriptorBufferInfo> descriptorBufferInfoCollection = new LinkedList<VkDescriptorBufferInfo>();
            descriptorBufferInfoCollection.add(descriptorBufferInfo);
            
            VkWriteDescriptorSet writeDescriptorSet = new VkWriteDescriptorSet();
            writeDescriptorSet.setDstSet(descriptorSetHandles.get(i));
            writeDescriptorSet.setDstBinding(0);
            writeDescriptorSet.setDstArrayElement(0);
            writeDescriptorSet.setDescriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER);
            writeDescriptorSet.setBufferInfo(descriptorBufferInfoCollection);
            
            Collection<VkWriteDescriptorSet> writeDescriptorSetCollection = new LinkedList<VkWriteDescriptorSet>();
            writeDescriptorSetCollection.add(writeDescriptorSet);
            
            vkUpdateDescriptorSets(vulkanLogicalDevice, writeDescriptorSetCollection, null);
        }
    }
    
    private void createDescriptorPool()
    {
        VkDescriptorPoolSize descriptorPoolSize =
                new VkDescriptorPoolSize(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER, swapchainImageReferences.size());
        
        Collection<VkDescriptorPoolSize> descriptorPoolSizeCollection = new LinkedList<VkDescriptorPoolSize>();
        descriptorPoolSizeCollection.add(descriptorPoolSize);
        
        VkDescriptorPoolCreateInfo descriptorPoolCreateInfo = new VkDescriptorPoolCreateInfo();
        descriptorPoolCreateInfo.setPoolSizes(descriptorPoolSizeCollection);
        descriptorPoolCreateInfo.setMaxSets(swapchainImageReferences.size());
        
        descriptorPoolHandle = new VkDescriptorPool();
        VkResult result = vkCreateDescriptorPool(vulkanLogicalDevice, descriptorPoolCreateInfo, null, descriptorPoolHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("failed to create descriptor pool!: " + vkResultToString(result));
        }
    }
    
    private void createUniformBuffers()
    {
        int bufferSizeInBytes = uniformBufferObject.sizeInBytes();
        
        log.trace("Creating uniform buffer object buffers");
        
        for (int i = 0; i < swapchainImageReferences.size(); i++)
        {
            BufferInformation uniformBufferInfo = createBuffer(
                    bufferSizeInBytes,
                    EnumSet.of(VkBufferUsageFlagBits.VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT),
                    EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT, VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT));
            
            uniformBufferInformationCollection.add(uniformBufferInfo);
        }
    }
    
    private void createDescriptorSetLayout()
    {
        VkDescriptorSetLayoutBinding descriptorSetLayoutBinding = new VkDescriptorSetLayoutBinding();
        descriptorSetLayoutBinding.setBinding(0);
        descriptorSetLayoutBinding.setDescriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER);
        descriptorSetLayoutBinding.setDescriptorCount(1);
        descriptorSetLayoutBinding.setStageFlags(EnumSet.of(VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT));
        
        VkDescriptorSetLayoutCreateInfo descriptorSetLayoutCreateInfo = new VkDescriptorSetLayoutCreateInfo();
        descriptorSetLayoutCreateInfo.setBindings(descriptorSetLayoutBinding);
        
        descriptorSetLayoutHandle = new VkDescriptorSetLayout();
        log.trace("descriptorSetLayoutHandle is {}", descriptorSetLayoutHandle.getHandleValue());
        
        VkResult result = vkCreateDescriptorSetLayout(
                vulkanLogicalDevice,
                descriptorSetLayoutCreateInfo,
                null,
                descriptorSetLayoutHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("failed to create descriptor set layout!: " + vkResultToString(result));
        }
        
        log.trace("descriptorSetLayoutHandle is {}", descriptorSetLayoutHandle.getHandleValue());
    }
    
    private void createIndexBuffer()
    {
        int indexBufferSizeInBytes = indices.length * 4 /* sizeof int in bytes */;

        BufferInformation stagingBufferInfo = createBuffer(
                (long)indexBufferSizeInBytes,
                EnumSet.of(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_SRC_BIT),
                EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT, VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT));
        
        MappedMemoryPointer pointerToMappedMemory = new MappedMemoryPointer();
        VkResult result = vkMapMemory(
                vulkanLogicalDevice,
                stagingBufferInfo.bufferMemoryHandle,
                0,
                indexBufferSizeInBytes,
                EnumSet.noneOf(VkMemoryMapFlagBits.class),
                pointerToMappedMemory);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to map memory: " + vkResultToString(result));
        }

        IntBuffer intBuffer = IntBuffer.allocate(indices.length);
        for (int i = 0; i < indices.length; i++)
        {
            intBuffer.put(indices[i]);
        }

        /*
         * Now we need to copy our vertex data into the virtual memory that the
         * graphics card will use to access the data.
         * 
         * Since this memory is in virtual address space i.e. outside the, for
         * lack of a better word, Java object reference space we need to use
         * a native method that can access both memory spaces.
         */
        pushDataToVirtualMemory(intBuffer, pointerToMappedMemory);
        
        vkUnmapMemory(vulkanLogicalDevice, stagingBufferInfo.bufferMemoryHandle);
        
        indexBufferInformation = createBuffer(
                indexBufferSizeInBytes,
                EnumSet.of(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_DST_BIT, VkBufferUsageFlagBits.VK_BUFFER_USAGE_INDEX_BUFFER_BIT),
                EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT));

        log.debug("Copying index buffer into device local storage");
        copyBuffer(stagingBufferInfo.bufferHandle, indexBufferInformation.bufferHandle, indexBufferSizeInBytes);
        log.debug("Copied index buffer into device local storage");

        vkDestroyBuffer(vulkanLogicalDevice, stagingBufferInfo.bufferHandle, null);
        vkFreeMemory(vulkanLogicalDevice, stagingBufferInfo.bufferMemoryHandle, null);
}
    
    private BufferInformation createBuffer(
            long bufferSizeInBytes,
            EnumSet<VkBufferUsageFlagBits> bufferUsageFlags,
            EnumSet<VkMemoryPropertyFlagBits> memoryPropertyFlags)
    {
        VkBufferCreateInfo bufferCreateInfo = new VkBufferCreateInfo();
        bufferCreateInfo.setSize(bufferSizeInBytes);
        bufferCreateInfo.setUsage(bufferUsageFlags);
        bufferCreateInfo.setSharingMode(VkSharingMode.VK_SHARING_MODE_EXCLUSIVE);

        /*
         * Get a handle to a Buffer (VkBuffer) that has the qualities defined
         * above.
         */
        VkBuffer bufferHandle = new VkBuffer();
        VkResult result = vkCreateBuffer(vulkanLogicalDevice, bufferCreateInfo, null, bufferHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create vertex buffer: " + vkResultToString(result));
        }
        
        /*
         * A handle to a buffer has been created, but,
         * it does not have any actual memory associated with it.
         * 
         * Now we need to find out what kind of memory is available.
         */
        VkMemoryRequirements memoryRequirements = new VkMemoryRequirements();
        
        vkGetBufferMemoryRequirements(vulkanLogicalDevice, bufferHandle, memoryRequirements);
        
        log.debug("VkMemoryRequirements size={}, alignment={}, memoryTypeBits={}",
                memoryRequirements.getSize(),
                memoryRequirements.getAlignment(),
                memoryRequirements.getMemoryTypeBits().toString());
        
        VkMemoryAllocateInfo memoryAllocateInfo = new VkMemoryAllocateInfo();
        memoryAllocateInfo.setAllocationSize(memoryRequirements.getSize());
        memoryAllocateInfo.setMemoryTypeIndex(findMemoryType(
                memoryRequirements.getMemoryTypeBits(),
                EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT, VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT)));
        
        /*
         * We need to allocate the memory for the buffer on the graphics card.
         * 
         * I am suspecting that the handle returned (VkDeviceMemory) is actually
         * a pointer in the address space on the graphics card itself.
         * 
         */
        VkDeviceMemory deviceMemoryHandle = new VkDeviceMemory();
        result = vkAllocateMemory(vulkanLogicalDevice, memoryAllocateInfo, null, deviceMemoryHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to allocate vertex memory: " + vkResultToString(result));
        }

        
        /*
         * Now, associate (bind) the memory just allocated on the graphics card with
         * the buffer handle(VkBuffer).
         */
        result = vkBindBufferMemory(vulkanLogicalDevice, bufferHandle, deviceMemoryHandle, 0);
        if (result != VkResult.VK_SUCCESS) {
            throw new AssertionError("Failed to bind buffer memory: " + vkResultToString(result));
        }

        BufferInformation temp = new BufferInformation();
        temp.bufferHandle       = bufferHandle;
        temp.bufferMemoryHandle = deviceMemoryHandle;
        
        return temp;
    }
    
    private void createVertexBuffer()
    {
        int positionSize = 2 /* for x and y */ * 4 /* for size of float */;
        int colorSize = 3 /* for r, g, b, */ * 4 /* for size of float */;
        int totalSizeInBytes = (positionSize + colorSize) * 4 /* for 4 vertices */;
        
        BufferInformation vertexStagingBufferInfo = createBuffer(
                (long)totalSizeInBytes,
                EnumSet.of(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_SRC_BIT),
                EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT, VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT));
        
        /*
         * Now we are getting a handle(MappedMemoryPointer) that corresponds to
         * the virtual memory address of the host accessible memory that
         * is also accessible to the graphics card.
         */
        MappedMemoryPointer pointerToMappedMemory = new MappedMemoryPointer();
        VkResult result = vkMapMemory(
                vulkanLogicalDevice,
                vertexStagingBufferInfo.bufferMemoryHandle,
                0,
                totalSizeInBytes,
                EnumSet.noneOf(VkMemoryMapFlagBits.class),
                pointerToMappedMemory);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to map memory: " + vkResultToString(result));
        }

        FloatBuffer floatBuffer = FloatBuffer.allocate(vertices.length * 5);
//        log.debug("FloatBuffer has array {}", floatBuffer.hasArray());

        for (int i = 0; i < vertices.length; i++)
        {
            Vertex vertex = vertices[i];
            floatBuffer.put(vertex.position.x);
            floatBuffer.put(vertex.position.y);
            floatBuffer.put(vertex.color.redComponent);
            floatBuffer.put(vertex.color.greenComponent);
            floatBuffer.put(vertex.color.blueComponent);
        }
        
        /*
         * Now we need to copy our vertex data into the virtual memory that the
         * graphics card will use to access the data.
         * 
         * Since this memory is in virtual address space i.e. outside the, for
         * lack of a better word, Java object reference space we need to use
         * a native method that can access both memory spaces.
         */
        pushDataToVirtualMemory(floatBuffer, pointerToMappedMemory);
        
        vkUnmapMemory(vulkanLogicalDevice, vertexStagingBufferInfo.bufferMemoryHandle);
        
        if (vertexBufferInformation == null)
        {
            vertexBufferInformation = createBuffer(
                    totalSizeInBytes,
                    EnumSet.of(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_DST_BIT, VkBufferUsageFlagBits.VK_BUFFER_USAGE_VERTEX_BUFFER_BIT),
                    EnumSet.of(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT));
        }
        
        /*
         * It really does not make sense in this case to use a staging buffer
         * since create vertex buffer is only called once during initialization.
         * This is mainly just to show how it is done.
         */
        copyBuffer(vertexStagingBufferInfo.bufferHandle, vertexBufferInformation.bufferHandle, totalSizeInBytes);
        
        vkDestroyBuffer(vulkanLogicalDevice, vertexStagingBufferInfo.bufferHandle, null);
        vkFreeMemory(vulkanLogicalDevice, vertexStagingBufferInfo.bufferMemoryHandle, null);
        
    }
    
    private void copyBuffer(VkBuffer sourceBuffer, VkBuffer destinationBuffer, long totalSizeInBytes)
    {
        VkCommandBufferAllocateInfo commandBufferAllocateInfo = new VkCommandBufferAllocateInfo();
        commandBufferAllocateInfo.setLevel(VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY);
        commandBufferAllocateInfo.setCommandPool(vulkanGraphicsCommandPoolHandle);
        commandBufferAllocateInfo.setCommandBufferCount(1);
        
        ArrayList<VkCommandBuffer> commandBufferCollection = new ArrayList<VkCommandBuffer>();
        commandBufferCollection.add(new VkCommandBuffer());
        
        VkResult result = vkAllocateCommandBuffers(vulkanLogicalDevice, commandBufferAllocateInfo, commandBufferCollection);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to allocate render command buffer: " + vkResultToString(result));
        }

        VkCommandBuffer commandBuffer = commandBufferCollection.get(0);
        VkCommandBufferBeginInfo commandBufferBeginInfo = new VkCommandBufferBeginInfo();
        commandBufferBeginInfo.setFlags(EnumSet.of(VkCommandBufferUsageFlagBits.VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT));
        
        result = vkBeginCommandBuffer(commandBuffer, commandBufferBeginInfo);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to begin recording command buffer: " + vkResultToString(result));
        }
        
        VkBufferCopy copyRegion = new VkBufferCopy(0L, 0L, totalSizeInBytes);
        Collection<VkBufferCopy> copyRegionCollection = new LinkedList<VkBufferCopy>();
        copyRegionCollection.add(copyRegion);
        
        vkCmdCopyBuffer(commandBuffer, sourceBuffer, destinationBuffer, copyRegionCollection);

        result = vkEndCommandBuffer(commandBuffer);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to end recording command buffer: " + vkResultToString(result));
        }

        VkSubmitInfo submitInfo = new VkSubmitInfo();
        submitInfo.setCommandBuffers(commandBuffer);
        
        Collection<VkSubmitInfo> submitInfoCollection = new LinkedList<VkSubmitInfo>();
        submitInfoCollection.add(submitInfo);
        
        result = vkQueueSubmit(vulkanGraphicsCommandsQueue, submitInfoCollection, null);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to submit command to vulkanGraphicsCommandsQueue: " + vkResultToString(result));
        }

        /*
         * I'm not sure if this should be here in a normal situation...this seems
         * to drag to world to a stop just to do this buffer copy.  I am thinking
         * that in a "production" case you would use a fence or something to allow
         * this to be done asynchronously.
         */
        vkQueueWaitIdle(vulkanGraphicsCommandsQueue);
        
        vkFreeCommandBuffers(vulkanLogicalDevice, vulkanGraphicsCommandPoolHandle, submitInfo.getCommandBuffers());
    }
    
    int findMemoryType(BitSet typeFilter, EnumSet<VkMemoryPropertyFlagBits> memoryPropertyFlags)
    {
        VkPhysicalDeviceMemoryProperties physicalDeviceMemoryProperties = new VkPhysicalDeviceMemoryProperties();
        vkGetPhysicalDeviceMemoryProperties(vulkanPhysicalDevice, physicalDeviceMemoryProperties);
        
        int i = 0;
//        int pick = -1;
        for (VkMemoryType vkMemoryType: physicalDeviceMemoryProperties.getMemoryTypes())
        {
            if (typeFilter.get(i) == true)
            {
                /*
                 * It appears in the spec that the first one that matches the 
                 * requirements is most likely the one you want.
                 */
                if (vkMemoryType.getPropertyFlags().containsAll(memoryPropertyFlags))
                {
                    return i;
                }
//                log.trace("Returning memoryTypes[{}] {} {}", i, vkMemoryType.getPropertyFlags().toString(), vkMemoryType.getHeapIndex());
//                if (vkMemoryType.getPropertyFlags().containsAll(memoryPropertyFlags) &&
//                        vkMemoryType.getPropertyFlags().size() == memoryPropertyFlags.size()    )
//                {
//                    pick = i;
//                }
            }
            
            i++;
        }
        
//        if (pick >= 0)
//        {
//            log.debug("Pick is {}", pick);
//            return pick;
//        }
//        for (int i = 0; i < physicalDeviceMemoryProperties.getMemoryTypes().size(); i++)
//        {
//            if ((typeFilter.get(i) &&
//                (physicalDeviceMemoryProperties.memoryTypes(i).propertyFlags() & memoryPropertyFlags) == memoryPropertyFlags)
//            {
//                physicalDeviceMemoryProperties.free();
//                log.trace("Returning memoryTypes[{}]", i);
//                return i;
//            }
//        }
//
//        physicalDeviceMemoryProperties.free();
//
        throw new AssertionError("Failed to find suitable memeory type.");
    }
    
    private void reCreateSwapchain()
    {
        log.trace("Waiting for vulkanLogicalDevice to go to idle.");
        vkDeviceWaitIdle(vulkanLogicalDevice);
        log.trace("vulkanLogicalDevice has gone to idle.");
        
        cleanupSwapchain(false);
        
        createSwapchain();
        
        createImageViews();
        
        createRenderPass();
        
        createGraphicsPipeline();
        
        createFrameBuffers();
        
        createCommandBuffers();
    }
    
    private void createSyncObjects()
    {
        VkSemaphoreCreateInfo semaphoreCreateInfo = new VkSemaphoreCreateInfo();
        
        VkFenceCreateInfo fenceCreateInfo = new VkFenceCreateInfo();
        fenceCreateInfo.setFlags(EnumSet.of(VkFenceCreateFlagBits.VK_FENCE_CREATE_SIGNALED_BIT));
        
        for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++)
        {
            VkSemaphore aSemaphore = new VkSemaphore();
            VkResult result = vkCreateSemaphore(vulkanLogicalDevice, semaphoreCreateInfo, null, aSemaphore);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to create imageAvailableSemaphoreHandle: " + vkResultToString(result));
            }
            
            imageAvailableSemaphoreHandles.add(aSemaphore);
            
            aSemaphore = new VkSemaphore();
            result = vkCreateSemaphore(vulkanLogicalDevice, semaphoreCreateInfo, null, aSemaphore);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to create renderFinishedSemaphoreHandle: " + vkResultToString(result));
            }
            
            renderFinishedSemaphoreHandles.add(aSemaphore);
            
            VkFence aFence = new VkFence();
            result = vkCreateFence(vulkanLogicalDevice, fenceCreateInfo, null, aFence);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to create renderFinishedSemaphoreHandle: " + vkResultToString(result));
            }
            
            inFlightFenceHandles.add(aFence);
        }
        
        
        log.trace("Created semaphores and fence");
    }
    
    private void createCommandBuffers()
    {
        VkCommandBufferAllocateInfo commandBufferAllocateInfo = new VkCommandBufferAllocateInfo();
        commandBufferAllocateInfo.setCommandPool(vulkanGraphicsCommandPoolHandle);
        commandBufferAllocateInfo.setLevel(VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY);
        commandBufferAllocateInfo.setCommandBufferCount(swapchainFramebufferReferences.size());
        
        Collection<VkCommandBuffer> commandBufferCollection = new LinkedList<VkCommandBuffer>();
        for (int i = 0; i < commandBufferAllocateInfo.getCommandBufferCount(); i++)
        {
            commandBufferCollection.add(new VkCommandBuffer());
        }
        
        VkResult result = vkAllocateCommandBuffers(vulkanLogicalDevice, commandBufferAllocateInfo, commandBufferCollection);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to allocate render command buffer: " + vkResultToString(result));
        }

        swapchainRenderCommandBuffers.addAll(commandBufferCollection);
        
        VkClearColorValue clearColorValue = new VkClearColorValue(0.0f, 0.0f, 0.0f, 0.0f);
        
        VkClearValue clearColor = new VkClearValue(clearColorValue);
        Collection<VkClearValue> clearColorCollection = new LinkedList<VkClearValue>();
        clearColorCollection.add(clearColor);
        
        // Begin command buffer recording
        for (int i = 0; i < swapchainRenderCommandBuffers.size(); i++)
        {
            VkCommandBuffer commandBuffer = swapchainRenderCommandBuffers.get(i);
            VkCommandBufferBeginInfo commandBufferBeginInfo = new VkCommandBufferBeginInfo();
            commandBufferBeginInfo.setFlags(EnumSet.of(VkCommandBufferUsageFlagBits.VK_COMMAND_BUFFER_USAGE_SIMULTANEOUS_USE_BIT));
            
            result = vkBeginCommandBuffer(commandBuffer, commandBufferBeginInfo);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to begin recording command buffer: " + vkResultToString(result));
            }
            
            VkRect2D renderArea = new VkRect2D(new VkOffset2D(0, 0),
                    new VkExtent2D(swapchainExtentUsed.getWidth(),
                            swapchainExtentUsed.getHeight()));
            
            VkRenderPassBeginInfo renderPassBeginInfo = new VkRenderPassBeginInfo();
            renderPassBeginInfo.setRenderPass(vulkanRenderPassHandle);
            renderPassBeginInfo.setFramebuffer(swapchainFramebufferReferences.get(i));
            renderPassBeginInfo.setClearValues(clearColorCollection);
            renderPassBeginInfo.setRenderArea(renderArea);
            
            vkCmdBeginRenderPass(commandBuffer, renderPassBeginInfo, VkSubpassContents.VK_SUBPASS_CONTENTS_INLINE);
            
            vkCmdBindPipeline(commandBuffer, VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS, vulkanPipelineHandle);
            
            Collection<VkBuffer> bufferCollection = new LinkedList<VkBuffer>();
            bufferCollection.add(vertexBufferInformation.bufferHandle);
            
            long[] offsets = new long[1];
            offsets[0] = 0L;

            log.debug("Binding vertex buffer for swapchainRenderCommandBuffer {}", i);
            vkCmdBindVertexBuffers(commandBuffer, 0, bufferCollection, offsets);

            vkCmdBindIndexBuffer(commandBuffer, indexBufferInformation.bufferHandle, 0L, VkIndexType.VK_INDEX_TYPE_UINT32);
            
            Collection<VkDescriptorSet> tempDescriptorSetCollection = new LinkedList<VkDescriptorSet>();
            tempDescriptorSetCollection.add(descriptorSetHandles.get(i));
            
            vkCmdBindDescriptorSets(
                    commandBuffer,
                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                    vulkanPipelineLayoutHandle,
                    0,
                    tempDescriptorSetCollection,
                    null);
            
            vkCmdDrawIndexed(commandBuffer, indices.length, 1, 0, 0, 0);
            
            vkCmdEndRenderPass(commandBuffer);
            
            result = vkEndCommandBuffer(commandBuffer);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to end recording command buffer: " + vkResultToString(result));
            }
        }
        
        log.trace("Finished creating command buffers.");
    }
    
    private void createCommandPool()
    {
        VkCommandPoolCreateInfo commandPoolCreateInfo = new VkCommandPoolCreateInfo();
        commandPoolCreateInfo.setQueueFamilyIndex(graphicsQueueFamilyIndex);
        
        vulkanGraphicsCommandPoolHandle = new VkCommandPool();
        VkResult result = vkCreateCommandPool(vulkanLogicalDevice, commandPoolCreateInfo, null, vulkanGraphicsCommandPoolHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create command pool: " + vkResultToString(result));
        }

        log.trace("Created command pool");
    }
    
    private void createFrameBuffers()
    {
        for (int i = 0; i < swapchainImageViewReferences.size(); i++)
        {
            Collection<VkImageView> imageViewCollection = new LinkedList<VkImageView>();
            imageViewCollection.add(swapchainImageViewReferences.get(i));
            
            VkFramebufferCreateInfo framebufferCreateInfo = new VkFramebufferCreateInfo();
            framebufferCreateInfo.setRenderPass(vulkanRenderPassHandle);
            framebufferCreateInfo.setAttachments(imageViewCollection);
            framebufferCreateInfo.setWidth(swapchainExtentUsed.getWidth());
            framebufferCreateInfo.setHeight(swapchainExtentUsed.getHeight());
            framebufferCreateInfo.setLayers(1);
            
            VkFramebuffer frameBufferHandle = new VkFramebuffer();
            VkResult result = vkCreateFramebuffer(vulkanLogicalDevice, framebufferCreateInfo, null, frameBufferHandle);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to framdebuffer for swapchain image " + i + " : " + vkResultToString(result));
            }
            
            swapchainFramebufferReferences.add(frameBufferHandle);
        }
        
        log.debug("Framebuffers created.");
    }
    
    private void createGraphicsPipeline()
    {
        VkShaderModule vertexShaderModuleReferenceHandle = new VkShaderModule();
        VkShaderModule fragmentShaderModuleReferenceHandle = new VkShaderModule();
        
        VkPipelineShaderStageCreateInfo vertexStageCreateInfo = null;
        VkPipelineShaderStageCreateInfo fragmentStageCreateInfo = null;
        
        try
        {
            vertexShaderModuleReferenceHandle =
                    loadShader(shaderPath + "VulkanTutorial5Shader.vert.spv", vulkanLogicalDevice);
            
            fragmentShaderModuleReferenceHandle =
                    loadShader(shaderPath + "VulkanTutorial5Shader.frag.spv", vulkanLogicalDevice);
            
            vertexStageCreateInfo = new VkPipelineShaderStageCreateInfo();
            vertexStageCreateInfo.setName("main");
            vertexStageCreateInfo.setModule(vertexShaderModuleReferenceHandle);
            vertexStageCreateInfo.setStage(VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT);
            
            fragmentStageCreateInfo = new VkPipelineShaderStageCreateInfo();
            fragmentStageCreateInfo.setName("main");
            fragmentStageCreateInfo.setModule(fragmentShaderModuleReferenceHandle);
            fragmentStageCreateInfo.setStage(VkShaderStageFlagBits.VK_SHADER_STAGE_FRAGMENT_BIT);
            
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Collection<VkPipelineShaderStageCreateInfo> shaderStageCreationInfoCollection = new LinkedList<VkPipelineShaderStageCreateInfo>();
        shaderStageCreationInfoCollection.add(vertexStageCreateInfo);
        shaderStageCreationInfoCollection.add(fragmentStageCreateInfo);
        
        VkVertexInputBindingDescription vertexInputBindingDescription =
                new VkVertexInputBindingDescription(
                        0 /* only one binding */,
                        (2 /* for position */ + 3 /* for color */) * 4 /* for size of float */,
                        VkVertexInputRate.VK_VERTEX_INPUT_RATE_VERTEX);
        
        Collection<VkVertexInputBindingDescription> vertexInputBindingDescriptionCollection = new LinkedList<VkVertexInputBindingDescription>();
        vertexInputBindingDescriptionCollection.add(vertexInputBindingDescription);
        
        /*
         * The format VK_FORMAT_R32G32_SFLOAT being used here does not refer to 
         * a color, but, rather indicates that the data is being sent to the
         * shader as 2 32bit floats (vec2 in the shader);
         * 
         * Below VK_FORMAT_R32G32B32_SFLOAT is used.  In this case it is indicating
         * that the data is being set to the shader as 3 32bit float (vec3 in
         * the shader).
         */
        Collection<VkVertexInputAttributeDescription> vertexAttributeDescriptionCollection = new LinkedList<VkVertexInputAttributeDescription>();

        VkVertexInputAttributeDescription vertexInputAttributeDescription =
                new VkVertexInputAttributeDescription(
                        0 /* corresponds to layout(location = 0) in vertex shader */,
                        0 /* only 1 binding */,
                        VkFormat.VK_FORMAT_R32G32_SFLOAT,
                        0 /* corresponds the the position element in the array to be built */);
        
        vertexAttributeDescriptionCollection.add(vertexInputAttributeDescription);
        
        vertexInputAttributeDescription =
                new VkVertexInputAttributeDescription(
                        1 /* corresponds to layout(location = 1) in vertex shader */,
                        0 /* only 1 binding */,
                        VkFormat.VK_FORMAT_R32G32B32_SFLOAT,
                        2 /* for x and y (position) */ * 4 /* size of float */);
        
        vertexAttributeDescriptionCollection.add(vertexInputAttributeDescription);
        
        VkPipelineVertexInputStateCreateInfo vertexInputStateCreateInfo = new VkPipelineVertexInputStateCreateInfo();
        vertexInputStateCreateInfo.setVertexBindingDescriptions(vertexInputBindingDescriptionCollection);
        vertexInputStateCreateInfo.setVertexAttributeDescriptions(vertexAttributeDescriptionCollection);
        
        log.trace("Vertex binding description count {}", vertexInputStateCreateInfo.getVertexBindingDescriptions().size());
        log.trace("Vertex attribute description count {}", vertexInputStateCreateInfo.getVertexAttributeDescriptions().size());
        
        VkPipelineInputAssemblyStateCreateInfo inputAssemblyStateCreateInfo = new VkPipelineInputAssemblyStateCreateInfo();
        inputAssemblyStateCreateInfo.setTopology(VkPrimitiveTopology.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST);
        inputAssemblyStateCreateInfo.setPrimitiveRestartEnable(false);
        
        VkViewport vulkanViewport = new VkViewport();
        vulkanViewport.setX(0.0f);
        vulkanViewport.setY(0.0f);
        vulkanViewport.setWidth((float)swapchainExtentUsed.getWidth());
        vulkanViewport.setHeight((float)swapchainExtentUsed.getHeight());
        vulkanViewport.setMinDepth(0.0f);
        vulkanViewport.setMaxDepth(1.0f);
        
        VkRect2D scissor = new VkRect2D(new VkOffset2D(0, 0), swapchainExtentUsed);
        VkPipelineViewportStateCreateInfo viewportStateCreateInfo = new VkPipelineViewportStateCreateInfo();
        viewportStateCreateInfo.setViewports(vulkanViewport);
        viewportStateCreateInfo.setScissors(scissor);
        
        VkPipelineRasterizationStateCreateInfo rasterizationStateCreateInfo = new VkPipelineRasterizationStateCreateInfo();
        rasterizationStateCreateInfo.setDepthClampEnable(false);
        rasterizationStateCreateInfo.setRasterizerDiscardEnable(false);
        rasterizationStateCreateInfo.setPolygonMode(VkPolygonMode.VK_POLYGON_MODE_FILL);
        rasterizationStateCreateInfo.setLineWidth(1.0f);
        rasterizationStateCreateInfo.setCullMode(EnumSet.of(VkCullModeFlagBits.VK_CULL_MODE_BACK_BIT));
        rasterizationStateCreateInfo.setFrontFace(VkFrontFace.VK_FRONT_FACE_COUNTER_CLOCKWISE);
        rasterizationStateCreateInfo.setDepthBiasEnable(false);
        rasterizationStateCreateInfo.setDepthBiasConstantFactor(0.0f);
        rasterizationStateCreateInfo.setDepthBiasClamp(0.0f);
        rasterizationStateCreateInfo.setDepthBiasSlopeFactor(0.0f);
        
        VkPipelineMultisampleStateCreateInfo multisampleStateCreateInfo = new VkPipelineMultisampleStateCreateInfo();
        multisampleStateCreateInfo.setSampleShadingEnable(false);
        multisampleStateCreateInfo.setRasterizationSamples(VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT);
        multisampleStateCreateInfo.setMinSampleShading(1.0f);
        multisampleStateCreateInfo.setSampleMask(null);
        multisampleStateCreateInfo.setAlphaToCoverageEnable(false);
        multisampleStateCreateInfo.setAlphaToOneEnable(false);

        VkPipelineColorBlendAttachmentState colorBlendAttachmentState = new VkPipelineColorBlendAttachmentState();
        colorBlendAttachmentState.setColorWriteMask(EnumSet.of(
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_R_BIT,
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_G_BIT,
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_B_BIT,
                VkColorComponentFlagBits.VK_COLOR_COMPONENT_A_BIT));
        colorBlendAttachmentState.setBlendEnable(false);
        colorBlendAttachmentState.setSrcColorBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ONE);
        colorBlendAttachmentState.setDstColorBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ZERO);
        colorBlendAttachmentState.setColorBlendOp(VkBlendOp.VK_BLEND_OP_ADD);
        colorBlendAttachmentState.setSrcAlphaBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ONE);
        colorBlendAttachmentState.setDstAlphaBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ZERO);
        colorBlendAttachmentState.setAlphaBlendOp(VkBlendOp.VK_BLEND_OP_ADD);

        Collection<VkPipelineColorBlendAttachmentState> colorBlendAttachmentStateCollection = new LinkedList<VkPipelineColorBlendAttachmentState>();
        colorBlendAttachmentStateCollection.add(colorBlendAttachmentState);
        
        VkPipelineColorBlendStateCreateInfo colorBlendStateCreateInfo = new VkPipelineColorBlendStateCreateInfo();
        colorBlendStateCreateInfo.setLogicOpEnable(false);
        colorBlendStateCreateInfo.setLogicOp(VkLogicOp.VK_LOGIC_OP_COPY);
        colorBlendStateCreateInfo.setAttachments(colorBlendAttachmentStateCollection);
        colorBlendStateCreateInfo.setRedBlendConstant(0.0f);
        colorBlendStateCreateInfo.setGreenBlendConstant(0.0f);
        colorBlendStateCreateInfo.setBlueBlendConstant(0.0f);
        colorBlendStateCreateInfo.setAlphaBlendConstant(0.0f);
        
        Collection<VkDynamicState> dynamicStateCollection = new LinkedList<VkDynamicState>();
        dynamicStateCollection.add(VkDynamicState.VK_DYNAMIC_STATE_VIEWPORT);
        dynamicStateCollection.add(VkDynamicState.VK_DYNAMIC_STATE_LINE_WIDTH);
        
        VkPipelineDynamicStateCreateInfo dynamicStateCreateInfo = new VkPipelineDynamicStateCreateInfo();
        dynamicStateCreateInfo.setDynamicStates(dynamicStateCollection);
        
        Collection<VkDescriptorSetLayout> descriptorSetLayoutCollection = new LinkedList<VkDescriptorSetLayout>();
        descriptorSetLayoutCollection.add(descriptorSetLayoutHandle);
        
        VkPipelineLayoutCreateInfo pipelineLayoutCreateInfo = new VkPipelineLayoutCreateInfo();
        pipelineLayoutCreateInfo.setSetLayouts(descriptorSetLayoutCollection);
        
        vulkanPipelineLayoutHandle = new VkPipelineLayout();
        
        VkResult result = vkCreatePipelineLayout(vulkanLogicalDevice, pipelineLayoutCreateInfo, null, vulkanPipelineLayoutHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create pipeline layout: " + vkResultToString(result));
        }

        VkGraphicsPipelineCreateInfo graphicsPipelineCreateInfo  = new VkGraphicsPipelineCreateInfo();
        graphicsPipelineCreateInfo.setStages(shaderStageCreationInfoCollection);
        graphicsPipelineCreateInfo.setVertexInputState(vertexInputStateCreateInfo);
        graphicsPipelineCreateInfo.setInputAssemblyState(inputAssemblyStateCreateInfo);
        graphicsPipelineCreateInfo.setViewportState(viewportStateCreateInfo);
        graphicsPipelineCreateInfo.setRasterizationState(rasterizationStateCreateInfo);
        graphicsPipelineCreateInfo.setMultisampleState(multisampleStateCreateInfo);
        graphicsPipelineCreateInfo.setColorBlendState(colorBlendStateCreateInfo);
        graphicsPipelineCreateInfo.setLayout(vulkanPipelineLayoutHandle);
        graphicsPipelineCreateInfo.setRenderPass(vulkanRenderPassHandle);
        graphicsPipelineCreateInfo.setSubpass(0);
        
        Collection<VkGraphicsPipelineCreateInfo> graphicsPipelineCreateInfoCollection = new LinkedList<VkGraphicsPipelineCreateInfo>();
        graphicsPipelineCreateInfoCollection.add(graphicsPipelineCreateInfo);
        
        /*
         * Make sure you have created and added one new VkPipeline element to
         * the pipelineHandleCollection for each VkGraphicsPipelineCreateInfo 
         * object passed to vkCreateGraphicsPipelines
         */
        LinkedList<VkPipeline> pipelineHandleCollection = new LinkedList<VkPipeline>();
        pipelineHandleCollection.add(new VkPipeline());
        
        result = vkCreateGraphicsPipelines(vulkanLogicalDevice,
                (VkPipelineCache)null,
                graphicsPipelineCreateInfoCollection,
                (VkAllocationCallbacks)null,
                pipelineHandleCollection);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to graphics pipeline: " + vkResultToString(result));
        }
        
        vulkanPipelineHandle = pipelineHandleCollection.getFirst();
        
        vkDestroyShaderModule(vulkanLogicalDevice, vertexShaderModuleReferenceHandle, null);
        vkDestroyShaderModule(vulkanLogicalDevice, fragmentShaderModuleReferenceHandle, null);
        
        log.debug("Graphics pipeline created.");
    }
    

    private void createRenderPass()
    {
        VkAttachmentDescription colorAttachment = new VkAttachmentDescription();
        colorAttachment.setFormat(swapchainImageFormatUsed);
        colorAttachment.setSamples(VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT);
        colorAttachment.setLoadOp(VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR);
        colorAttachment.setStoreOp(VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_STORE);
        colorAttachment.setStencilLoadOp(VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE);
        colorAttachment.setStencilStoreOp(VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE);
        colorAttachment.setInitialLayout(VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED);
        colorAttachment.setFinalLayout(VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR);

        Collection<VkAttachmentDescription> colorAttachmentCollection = new LinkedList<VkAttachmentDescription>();
        colorAttachmentCollection.add(colorAttachment);
        
        VkAttachmentReference colorAttachmentReference = new VkAttachmentReference();
        colorAttachmentReference.setAttachment(0);
        colorAttachmentReference.setLayout(VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL);

        Collection<VkAttachmentReference> colorAttachmentReferenceCollection = new LinkedList<VkAttachmentReference>();
        colorAttachmentReferenceCollection.add(colorAttachmentReference);
        
        VkSubpassDescription subpassDescription = new VkSubpassDescription();
        subpassDescription.setPipelineBindPoint(VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS);
        subpassDescription.setColorAttachments(colorAttachmentReferenceCollection);
        
        Collection<VkSubpassDescription> subpassDescriptionCollection = new LinkedList<VkSubpassDescription>();
        subpassDescriptionCollection.add(subpassDescription);

        VkSubpassDependency subpassDependency = new VkSubpassDependency();
        subpassDependency.setSrcSubpass(VulkanConstants.VK_SUBPASS_EXTERNAL);
        subpassDependency.setDstSubpass(0);
        subpassDependency.setSrcStageMask(EnumSet.of(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT));
        subpassDependency.setSrcAccessMask(EnumSet.noneOf(VkAccessFlagBits.class));
        subpassDependency.setDstStageMask(EnumSet.of(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT));
        subpassDependency.setDstAccessMask(EnumSet.of(
                VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_READ_BIT,
                VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT));
        
        Collection<VkSubpassDependency> subpassDependencyCollection = new LinkedList<VkSubpassDependency>();
        subpassDependencyCollection.add(subpassDependency);
        
        VkRenderPassCreateInfo renderPassCreateInfo = new VkRenderPassCreateInfo();
        renderPassCreateInfo.setAttachments(colorAttachmentCollection);
        renderPassCreateInfo.setSubpasses(subpassDescriptionCollection);
        renderPassCreateInfo.setDependencies(subpassDependencyCollection);
        
        vulkanRenderPassHandle = new VkRenderPass();
        
        VkResult result = vkCreateRenderPass(vulkanLogicalDevice, renderPassCreateInfo, null, vulkanRenderPassHandle);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create render pass: " + vkResultToString(result));

        }
        
        log.debug("RenderPass created successfully.");
    }
    
    private void createImageViews()
    {
        VkComponentMapping components = new VkComponentMapping();
        components.setR(VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY);
        components.setG(VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY);
        components.setB(VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY);
        components.setA(VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY);

        VkImageSubresourceRange subresourceRange = new VkImageSubresourceRange();
        subresourceRange.setAspectMask(EnumSet.of(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT));
        subresourceRange.setBaseMipLevel(0);
        subresourceRange.setLevelCount(1);
        subresourceRange.setBaseArrayLayer(0);
        subresourceRange.setLayerCount(1);
        
        for (VkImage swapchainImageReference : swapchainImageReferences)
        {
            VkImageViewCreateInfo swapchainImageViewInfo = new VkImageViewCreateInfo();
            
            swapchainImageViewInfo.setImage(swapchainImageReference);
            swapchainImageViewInfo.setViewType(VkImageViewType.VK_IMAGE_VIEW_TYPE_2D);
            swapchainImageViewInfo.setFormat(swapchainImageFormatUsed);
            swapchainImageViewInfo.setComponents(components);
            swapchainImageViewInfo.setSubresourceRange(subresourceRange);
            
            VkImageView imageViewHandle = new VkImageView();
            
            VkResult result = vkCreateImageView(vulkanLogicalDevice, swapchainImageViewInfo, null, imageViewHandle);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to create image view: " + vkResultToString(result));
            }
            
            swapchainImageViewReferences.add(imageViewHandle);
            
        }
        
        log.trace("Created the swapchain image views.");
    }
    
    private void createSwapchain()
    {
        VkSwapchainKHR oldSwapchainHandle = null;
        if (vulkanSwapchainHandle != null)
        {
            oldSwapchainHandle = vulkanSwapchainHandle;
        }
        
        VkSurfaceFormatKHR bestImageFormat = chooseSwapchainImageFormat();

        swapchainImageFormatUsed = bestImageFormat.getFormat();
        VkColorSpaceKHR colorSpace = bestImageFormat.getColorSpace();
        
        VkPresentModeKHR bestSwapchainPresentationMode = chooseSwapchainPresentationMode();
        
        swapchainExtentUsed = chooseSwapExtent();
        
        // Try and have a queue length 1 more than the minimum
        int imageCount = swapchainSupportDetails.surfaceCapabilities.getMinImageCount() + 1;
        
        /*
         * If our "try for" image count is too big just settle for the maximum
         */
        if (swapchainSupportDetails.surfaceCapabilities.getMaxImageCount() > 0 &&
            imageCount > swapchainSupportDetails.surfaceCapabilities.getMaxImageCount())
        {
            imageCount = swapchainSupportDetails.surfaceCapabilities.getMaxImageCount();
        }
        
        VkSwapchainCreateInfoKHR swapchainCreateInfo = new VkSwapchainCreateInfoKHR();
        swapchainCreateInfo.setSurface(vulkanSurface);
        swapchainCreateInfo.setMinImageCount(imageCount);
        swapchainCreateInfo.setImageFormat(swapchainImageFormatUsed);
        swapchainCreateInfo.setImageColorSpace(colorSpace);
        swapchainCreateInfo.setImageExtent(swapchainExtentUsed);
        swapchainCreateInfo.setImageArrayLayers(1);
        swapchainCreateInfo.setImageUsage(EnumSet.of(VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT));
        swapchainCreateInfo.setImageSharingMode(VkSharingMode.VK_SHARING_MODE_CONCURRENT);
        swapchainCreateInfo.setPreTransform(swapchainSupportDetails.surfaceCapabilities.getCurrentTransform());
        swapchainCreateInfo.setCompositeAlpha(VkCompositeAlphaFlagBitsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR);
        swapchainCreateInfo.setPresentMode(bestSwapchainPresentationMode);
        swapchainCreateInfo.setClipped(true);
        swapchainCreateInfo.setOldSwapchain(oldSwapchainHandle);
        
        if (graphicsQueueFamilyIndex != presentationQueueFamilyIndex)
        {
            log.trace("The queue family indices are different.");
            int[] queueFamilyIndices = new int[2];
            queueFamilyIndices[0] = graphicsQueueFamilyIndex;
            queueFamilyIndices[1] = presentationQueueFamilyIndex;
            swapchainCreateInfo.setQueueFamilyIndices(queueFamilyIndices);
            swapchainCreateInfo.setImageSharingMode(VkSharingMode.VK_SHARING_MODE_CONCURRENT);
        }
        else
        {
            log.trace("The queue family indices are the same.");
            swapchainCreateInfo.setQueueFamilyIndices(null);
            swapchainCreateInfo.setImageSharingMode(VkSharingMode.VK_SHARING_MODE_EXCLUSIVE);
        }
        
        
        vulkanSwapchainHandle = new VkSwapchainKHR();
        
        VkResult result = vkCreateSwapchainKHR(vulkanLogicalDevice, swapchainCreateInfo, (VkAllocationCallbacks)null, vulkanSwapchainHandle);
        
        log.trace("Swapchain handle is {}", vulkanSwapchainHandle);
        
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create swap chain: " + vkResultToString(result));
        }
        
        log.trace("swapchain created.");

        /*
         * If oldSwapchainHandle is not null we need to go ahead and destroy
         * the old swapchain now that we have created our shiny new one.
         */
        if (oldSwapchainHandle != null &&
            oldSwapchainHandle.getHandleValue() != 0L)
        {
            log.trace("Attempting to destroy the old swapchain.");
            vkDestroySwapchainKHR(vulkanLogicalDevice, oldSwapchainHandle, null);
            log.trace("Destroyed the old swapchain.");
        }
        
        result = vkGetSwapchainImagesKHR(vulkanLogicalDevice, vulkanSwapchainHandle, swapchainImageReferences);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create swap chain: " + vkResultToString(result));
        }

        log.trace("{} swapchain image references saved.", swapchainImageReferences.size());
    }
    
    private VkPresentModeKHR chooseSwapchainPresentationMode()
    {
        VkPresentModeKHR bestMode = VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR;
        
        for (VkPresentModeKHR presentationMode : swapchainSupportDetails.presentationModes)
        {
            /*
             * Basically, take KHRSurface.VK_PRESENT_MODE_MAILBOX_KHR if
             * we can get it.
             */
            if (presentationMode == VkPresentModeKHR.VK_PRESENT_MODE_IMMEDIATE_KHR &&
                bestMode != VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR)
            {
                bestMode = VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR;
            }
            else if (presentationMode == VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR)
            {
                bestMode = VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR;
            }
        }
        
        if (bestMode == VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR)
            log.debug("VK_PRESENT_MODE_FIFO_KHR presentation mode selected.");
        else if (bestMode == VkPresentModeKHR.VK_PRESENT_MODE_IMMEDIATE_KHR)
            log.debug("VK_PRESENT_MODE_IMMEDIATE_KHR presentation mode selected.");
        else
            log.debug("VK_PRESENT_MODE_MAILBOX_KHR presentation mode selected.");
        
        return bestMode;
    }
    
    private VkExtent2D chooseSwapExtent()
    {
        if (swapchainSupportDetails.surfaceCapabilities.getCurrentImageExtent().getWidth() != -1)
        {
            return swapchainSupportDetails.surfaceCapabilities.getCurrentImageExtent();
        }
        else
        {
//            IntBuffer pWidth = memAllocInt(1);
//            IntBuffer pHeight = memAllocInt(1);
//            
//            glfwGetFramebufferSize(windowHandle, pWidth, pHeight);
//            
//            windowWidth = pWidth.get(0);
//            windowHeight = pHeight.get(0);
//            
//            memFree(pWidth);
//            memFree(pHeight);
//            
            VkExtent2D actualExtent = new VkExtent2D(windowWidth, windowHeight);
            
            int winW = Math.max(
                    swapchainSupportDetails.surfaceCapabilities.getMinImageExtent().getWidth(),
                    Math.min(swapchainSupportDetails.surfaceCapabilities.getMaxImageExtent().getWidth(), actualExtent.getWidth()));
            
            int winH = Math.max(
                    swapchainSupportDetails.surfaceCapabilities.getMinImageExtent().getHeight(),
                    Math.min(swapchainSupportDetails.surfaceCapabilities.getMaxImageExtent().getHeight(), actualExtent.getHeight()));
            
            actualExtent.setWidth(winW);
            actualExtent.setHeight(winH);
            
            return actualExtent;
        }
    }
    
    private VkSurfaceFormatKHR chooseSwapchainImageFormat()
    {
        if (swapchainSupportDetails.surfaceFormats.size() == 1)
        {
            VkSurfaceFormatKHR surfaceFormat = swapchainSupportDetails.surfaceFormats.iterator().next();
            if (surfaceFormat.getFormat() == VkFormat.VK_FORMAT_UNDEFINED)
            {
                log.debug("Best case scenario found, but, we have to try the hard way.");
            }
        }
        
        for (VkSurfaceFormatKHR surfaceFormat : swapchainSupportDetails.surfaceFormats)
        {
            log.debug("Surface format available {}", surfaceFormat.getFormat());
            if (surfaceFormat.getFormat() == VkFormat.VK_FORMAT_B8G8R8A8_UNORM &&
                surfaceFormat.getColorSpace() == VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR)
            {
                log.debug("Found the most desired swap surface format and colorspace.");
                return surfaceFormat;
            }
        }
        
        /*
         * Well, we didn't find a desired one so we are just going to take the first.
         */
        
        log.debug("Most desired swap surface format not found so just going with the first.");
        return swapchainSupportDetails.surfaceFormats.iterator().next();
    }
    
    private void testSwapchainSuitability()
    {
        /*
         * So far, the swapchain is suitable if there is at least one
         * supported image format and at least one supported presentation
         * mode for the window surface we have.
         */
        
        if (swapchainSupportDetails.surfaceFormats.isEmpty() == false &&
            swapchainSupportDetails.presentationModes.isEmpty() == false)
        {
            log.trace("Swapchain seems to be suitable.");
            return;
        }
        
        throw new AssertionError("The swapchain is NOT suitable.");
    }
    

    private void retrieveSwapchainSupportDetails()
    {
        VkSurfaceCapabilitiesKHR surfaceCapabilities = new VkSurfaceCapabilitiesKHR();
        VkResult result = vkGetPhysicalDeviceSurfaceCapabilitiesKHR(vulkanPhysicalDevice, vulkanSurface, surfaceCapabilities);
        if (result != VkResult.VK_SUCCESS)
        {
            log.error("Call to vkGetPhysicalDeviceSurfaceCapabilitiesKHR failed. Return code is:{}.", result);
            return;
        }
        
        List<VkSurfaceFormatKHR> surfaceFormats = new LinkedList<VkSurfaceFormatKHR>();
        result = vkGetPhysicalDeviceSurfaceFormatsKHR(vulkanPhysicalDevice, vulkanSurface, surfaceFormats); 
        if (result != VkResult.VK_SUCCESS)
        {
            log.error("Call to vkGetPhysicalDeviceSurfaceFormatsKHR failed. Return code is:{}.", result);
            return;
        }
        
        log.debug("{} surface formats retrieved.", surfaceFormats.size());
        
        List<VkPresentModeKHR> presentationModes = new LinkedList<VkPresentModeKHR>();
        result = vkGetPhysicalDeviceSurfacePresentModesKHR(vulkanPhysicalDevice, vulkanSurface, presentationModes); 
        if (result != VkResult.VK_SUCCESS)
        {
            log.error("Call to vkGetPhysicalDeviceSurfacePresentModesKHR failed. Return code is:{}.", result);
            return;
        }
        
        /////////////////////////////////////////////////////////////

        if (swapchainSupportDetails != null)
        {
            swapchainSupportDetails.clean();
        }
        
        swapchainSupportDetails = new SwapchainSupportDetails(surfaceCapabilities, surfaceFormats, presentationModes);

        log.trace("Retrieved swapchain support details.");
    }
    
    private void createPresentationCommandQueue()
    {
        vulkanPresentationCommandsQueue = new VkQueue();
        
        vkGetDeviceQueue(vulkanLogicalDevice, presentationQueueFamilyIndex, 0, vulkanPresentationCommandsQueue);

        log.trace("Handle for Presentation command queue created.");
    }
    
    private void findPresentationQueueFamily()
    {
        boolean presentationIsSupported[] = new boolean[totalNumberOfQueueFamilies];
        for (int i = 0; i < totalNumberOfQueueFamilies; i++)
        {
            VkResult result = vkGetPhysicalDeviceSurfaceSupportKHR(
                    vulkanPhysicalDevice,
                    i,
                    vulkanSurface,
                    presentationIsSupported);
            if (result != VkResult.VK_SUCCESS)
            {
                throw new AssertionError("Failed to get physical device surface support: " + vkResultToString(result));
            }
        }
        log.debug("Supported [0] ={}.", presentationIsSupported[0]);
        log.debug("Supported [1] ={}.", presentationIsSupported[1]);
        /*
         * For now anyway we are going to consider it a best case situation in
         * the case where the graphics commands queue and the presentation
         * commands queue are in the same queue family.
         */
        presentationQueueFamilyIndex = -1;
        boolean bestCaseAvailable = false;
        for (int i = 0; i < totalNumberOfQueueFamilies; i++)
        {
            if (presentationIsSupported[i] == true)
            {
                if (graphicsQueueFamilyIndex == i)
                {
                    bestCaseAvailable = true;
                    break;
                }
                else
                    presentationQueueFamilyIndex = i;
            }
        }
        
        if (bestCaseAvailable == true)
        {
            presentationQueueFamilyIndex = graphicsQueueFamilyIndex;
        }
        
        if (presentationQueueFamilyIndex < 0)
        {
            throw new AssertionError("Failed to find a presentation queue");
        }

        log.trace("Presentation command queue index is {}.", presentationQueueFamilyIndex);
        
    }
    
    private void createWindowSurface()
    {
        VkWaylandSurfaceCreateInfoKHR surfaceCreateInfo = new VkWaylandSurfaceCreateInfoKHR();
        surfaceCreateInfo.setWlDisplay(waylandDisplay.getHandle());
        surfaceCreateInfo.setWlSurface(waylandSurface.getHandle());
        
        vulkanSurface = new VkSurfaceKHR();
        
        VkResult result = vkCreateWaylandSurfaceKHR(
                vulkanInstance,
                surfaceCreateInfo,
                null,
                vulkanSurface);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError(
                    "Failed to create device: " + vkResultToString(result));
        }
        
        log.debug("VkSurfaceKHR created");
        //        LongBuffer pSurface = memAllocLong(1);
//        int result = glfwCreateWindowSurface(vulkanInstance, windowHandle, null, pSurface);
//        windowSurfaceHandle = pSurface.get(0);
//        if (result != VK_SUCCESS)
//        {
//            throw new AssertionError("Failed to create surface: " + translateVulkanResult(result));
//        }
//        
//        log.trace("Window surface created.");
//        memFree(pSurface);
    }
    
    private void createGraphicsCommandQueue()
    {
        vulkanGraphicsCommandsQueue = new VkQueue();
        vkGetDeviceQueue(vulkanLogicalDevice, graphicsQueueFamilyIndex, 0, vulkanGraphicsCommandsQueue);
        log.trace("Handle for Graphics command queue created. {} {}", vulkanLogicalDevice.toString(), vulkanGraphicsCommandsQueue.toString());
    }
    
    private void findQueueFamilies()
    {
        LinkedList<VkQueueFamilyProperties> queueFamilyProperties = new LinkedList<VkQueueFamilyProperties>();
        vkGetPhysicalDeviceQueueFamilyProperties(vulkanPhysicalDevice, queueFamilyProperties);

        totalNumberOfQueueFamilies = queueFamilyProperties.size();
        
        log.trace("{} queue families were found.", totalNumberOfQueueFamilies);
        
        int i = 0;
        for (VkQueueFamilyProperties queueFamilyProperty : queueFamilyProperties)
        {
            if (queueFamilyProperty.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT) == true)
            {
                graphicsQueueFamilyIndex = i;
                break;
            }

//            if (queueFamilyProperty.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT) == true)
//            {
//                graphicsQueueFamilyIndex = i;
//                log.trace("Queue Family [{}] supports GRAPHICS commands.", i);
//            }
//
//            if (queueFamilyProperty.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_TRANSFER_BIT) == true)
//                log.trace("Queue Family [{}] supports TRANSFER commands.", i);
//
//            if (queueFamilyProperty.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_SPARSE_BINDING_BIT) == true)
//                log.trace("Queue Family [{}] supports SPARSE_BINDING commands.", i);
//
//            if (queueFamilyProperty.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_COMPUTE_BIT) == true)
//                log.trace("Queue Family [{}] supports COMPUTE commands.", i);
//
            i++;
        }

        log.debug("The queue family at index {} contains the Graphics Command Queue.", graphicsQueueFamilyIndex);
    }
    
    private void createLogicalDevice()
    {
        float[] queuePriorities = new float[1];
        queuePriorities[0] = 1.0f;
        
        VkDeviceQueueCreateInfo vkDeviceQueueCreateInfo = new VkDeviceQueueCreateInfo();
        vkDeviceQueueCreateInfo.setQueueFamilyIndex(graphicsQueueFamilyIndex);
        vkDeviceQueueCreateInfo.setQueueCount(1);
        vkDeviceQueueCreateInfo.setQueuePriorities(queuePriorities);
        
        Collection<VkDeviceQueueCreateInfo> vkDeviceQueueCreateInfos = new ArrayList<VkDeviceQueueCreateInfo>();
        vkDeviceQueueCreateInfos.add(vkDeviceQueueCreateInfo);

        VkDeviceCreateInfo vkDeviceCreateInfo = new VkDeviceCreateInfo();
        vkDeviceCreateInfo.setQueueCreateInfos(vkDeviceQueueCreateInfos);
        vkDeviceCreateInfo.setEnabledFeatures(chosenPhysicalDeviceFeatures);
        vkDeviceCreateInfo.setEnabledExtensionNames(vulkanGraphicsDeviceExtensionNames);

        VkDevice logicalDevice = new VkDevice();
        
        VkResult result = vkCreateDevice(vulkanPhysicalDevice, vkDeviceCreateInfo, null, logicalDevice);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError(
                    "Failed to create device: " + vkResultToString(result));
        }

        vulkanLogicalDevice = logicalDevice;

        log.debug("Logical Vulkan device created {}.", vulkanLogicalDevice.toString());
    }
    
    private void setupDeviceExtensions()
    {
        vulkanGraphicsDeviceExtensionNames = null;
        vulkanGraphicsDeviceExtensionNames = new ArrayList<String>();
        vulkanGraphicsDeviceExtensionNames.add(VK_KHR_SWAPCHAIN_EXTENSION_NAME);
    }

    private class ScoreResult
    {
        int score;
        int queueFamilyIndex;
        int numberOfQueues;
        VkPhysicalDeviceFeatures   deviceFeatures;
        
        ScoreResult()
        {
            score = -1;
            queueFamilyIndex = -1;
            numberOfQueues = 0;
        }

    }
    
    private void pickPhysicalDevice()
    {
        LinkedList<VkPhysicalDevice> physicalDeviceList = new LinkedList<VkPhysicalDevice>();
        
        VkResult result = vkEnumeratePhysicalDevices(vulkanInstance, physicalDeviceList);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to get list of physical devices: " + vkResultToString(result));
        }
        
        if (physicalDeviceList.size() == 0)
        {
            throw new AssertionError("There is no Vulkan support available.");
        }
        
        log.debug("{} Vulkan device(s) have been found on the system.", physicalDeviceList.size());
        
//        log.debug("handle is {}", String.format("%X", physicalDeviceList.get(0).getHandle()));
        
        int oldScore = -1;
        int graphicsQueueIndex = -1;
        int numberOfQueues = 0;
        VkPhysicalDeviceFeatures deviceFeatures = null;
        VkPhysicalDevice bestDevice = null;
        for (VkPhysicalDevice physicalDevice : physicalDeviceList)
        {
            ScoreResult scoreResult = getPhysicalDeviceScore(physicalDevice);
            int currentScore = scoreResult.score;
            if (currentScore > 0)
            {
                // currentScore must be > 0 to be suitable at all
                if (currentScore > oldScore)
                {
                    bestDevice = physicalDevice;
                    oldScore   = currentScore;
                    graphicsQueueIndex = scoreResult.queueFamilyIndex;
                    numberOfQueues = scoreResult.numberOfQueues;
                    deviceFeatures = scoreResult.deviceFeatures;
                }
            }
        }
        
        if (bestDevice == null)
        {
            throw new AssertionError("Unable to find a suitable GPU.");
        }

        // We have chosen the graphics device
        vulkanPhysicalDevice = bestDevice;
        
        // We have chosen the queue family for that device
        graphicsQueueFamilyIndex = graphicsQueueIndex;
        
        // We are saving the total number of queue families for this device
        totalNumberOfQueueFamilies = numberOfQueues;
        
        // We are saving the device features for the chosen device
        chosenPhysicalDeviceFeatures = deviceFeatures;
    }
    
    private ScoreResult getPhysicalDeviceScore(VkPhysicalDevice physicalDevice)
    {
        ScoreResult scoreResult = new ScoreResult();
        
        VkPhysicalDeviceFeatures   deviceFeatures   = new VkPhysicalDeviceFeatures();
        vkGetPhysicalDeviceFeatures(physicalDevice, deviceFeatures);
        if (deviceFeatures.isGeometryShader() == false)
        {
            /*
             * This will most likely never happen.  In either case it is a
             * no go situation.
             */
            scoreResult.score = -1;
            return scoreResult;
        }
        
        LinkedList<VkExtensionProperties> extensionProperties = new LinkedList<VkExtensionProperties>();
        VkResult result = vkEnumerateDeviceExtensionProperties(physicalDevice, null, extensionProperties);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to get device extension properties: " + vkResultToString(result));
        }

        /*
         * Now we are going to make sure the swapchain extension is available
         */
        boolean foundTheExtensions = false;
        for (VkExtensionProperties extensionProperty : extensionProperties)
        {
//            log.debug("Extension Name {}", extensionProperty.getExtensionName());
            if (extensionProperty.getExtensionName().equals(VK_KHR_SWAPCHAIN_EXTENSION_NAME) == true)
            {
                foundTheExtensions = true;
                break;
            }
        }
        
        if (foundTheExtensions == false)
        {
            log.error("Required extension of {} not available in device.", VK_KHR_SWAPCHAIN_EXTENSION_NAME);
            scoreResult.score = -1;
            return scoreResult;
        }
        
        LinkedList<VkQueueFamilyProperties> queueFamilyProperties = new LinkedList<VkQueueFamilyProperties>();
        vkGetPhysicalDeviceQueueFamilyProperties(physicalDevice, queueFamilyProperties);
        
        int queueFamilyIndex = -1;
        int index = 0;
        for (VkQueueFamilyProperties queueFamily : queueFamilyProperties)
        {
            if (queueFamily.getQueueFlags().contains(VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT) == true)
            {
                queueFamilyIndex = index;
                break;
            }
            index++;
        }
        
        if (queueFamilyIndex == -1)
        {
            /*
             * For this device to be considered it must have a queue with
             * the VK_QUEUE_GRAPHICS_BIT bit set.
             */
            scoreResult.score = -1;
            return scoreResult;
        }
        else
        {
            scoreResult.queueFamilyIndex = queueFamilyIndex;
            scoreResult.numberOfQueues   = queueFamilyProperties.size();
            scoreResult.deviceFeatures   = deviceFeatures;
        }
        
        VkPhysicalDeviceProperties deviceProperties = new VkPhysicalDeviceProperties();
        vkGetPhysicalDeviceProperties(physicalDevice, deviceProperties);
        
        // Maximum possible size of textures affects graphics quality
        // FIXME should this be for 3D in my final use case?
        scoreResult.score = deviceProperties.getLimits().getMaxImageDimension2D();

        /*
         * TODO
         * These scores need to be balanced with the getMaxImageDimension2D
         */
        switch(deviceProperties.getDeviceType())
        {
            case VK_PHYSICAL_DEVICE_TYPE_CPU:
                scoreResult.score += 1 + deviceProperties.getLimits().getMaxImageDimension2D();
                break;
            case VK_PHYSICAL_DEVICE_TYPE_DISCRETE_GPU:
                scoreResult.score += 1000 + deviceProperties.getLimits().getMaxImageDimension2D();
                break;
            case VK_PHYSICAL_DEVICE_TYPE_INTEGRATED_GPU:
                scoreResult.score += 100 + deviceProperties.getLimits().getMaxImageDimension2D();
                break;
            case VK_PHYSICAL_DEVICE_TYPE_VIRTUAL_GPU:
                scoreResult.score += 2 + deviceProperties.getLimits().getMaxImageDimension2D();
                break;
            default:
                // No suitable device type
                scoreResult.score = -1;
                return scoreResult;
        }
        
        return scoreResult;
    }

    private void createVulkanInstance()
    {
        VkApplicationInfo vulkanApplicationInfo = new VkApplicationInfo();
        vulkanApplicationInfo.setApplicationName("Hello Triangle");
        vulkanApplicationInfo.setApplicationVersion(0);
        vulkanApplicationInfo.setEngineName("No Engine");
        vulkanApplicationInfo.setEngineVersion(0);
        vulkanApplicationInfo.setApiVersion(VK_MAKE_VERSION(1, 77, 0));
        
        log.trace("Created the Vulkan application information.");
        
        // FIXME
//        /* Look for instance extensions */
//        PointerBuffer requiredExtensions = glfwGetRequiredInstanceExtensions();
//        if (requiredExtensions == null)
//        {
//            throw new AssertionError("Failed to find list of required Vulkan extensions");
//        }
//
        ArrayList<String> requiredExtensions = new ArrayList<String>();
        requiredExtensions.add(VK_KHR_SURFACE_EXTENSION_NAME);
        
        log.debug("Validation layers enabled = {}.", validationDesired);
        
        ArrayList<String> enabledExtensions = new ArrayList<String>();
        
        enabledExtensions.addAll(requiredExtensions);
        enabledExtensions.add(VK_EXT_DEBUG_REPORT_EXTENSION_NAME);
        enabledExtensions.add(VK_KHR_WAYLAND_SURFACE_EXTENSION_NAME);
        
        // Setup the desired/required validation layers (as defined above)
        if (validationDesired == true)
        {
            for (int i = 0; i < validationLayers.length; i++)
            {
                vulkanGraphicsenabledLayerNames.add(validationLayers[i]);
            }
        }
        
        VkInstanceCreateInfo vulkanInstanceCreateInfo = new VkInstanceCreateInfo();
        vulkanInstanceCreateInfo.setFlags(EnumSet.noneOf(VkInstanceCreateFlagBits.class));
        vulkanInstanceCreateInfo.setApplicationInfo(vulkanApplicationInfo);
        vulkanInstanceCreateInfo.setEnabledExtensionNames(enabledExtensions);
        vulkanInstanceCreateInfo.setEnabledLayerNames(vulkanGraphicsenabledLayerNames);

        log.trace("Created the Vulkan instance creation information.");

        vulkanInstance = new VkInstance();
        VkResult result = vkCreateInstance(vulkanInstanceCreateInfo, (VkAllocationCallbacks)null, vulkanInstance);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create VkInstance: " + vkResultToString(result));
        }

        log.trace("Created the Vulkan instance.  Instance number (the handle) is {}", String.format("%X", vulkanInstance.getHandleValue()));
        
        if (validationDesired == true)
        {
            VkDebugReportCallbackCreateInfoEXT debugReportCallbackCreateInfoEXT = new VkDebugReportCallbackCreateInfoEXT();
            debugReportCallbackCreateInfoEXT.setFlags(
                    EnumSet.of(VkDebugReportFlagBitsEXT.VK_DEBUG_REPORT_ERROR_BIT_EXT,
                               VkDebugReportFlagBitsEXT.VK_DEBUG_REPORT_WARNING_BIT_EXT));
            
            debugReportCallbackCreateInfoEXT.setCallbackObject(myDebugCallback);
            
            debugReportCallbackCreateInfoEXT.setUserData(null);
//                debugReportCallbackCreateInfoEXT.setUserData(userData);
            
            debugCallbackHandle = new VkDebugReportCallbackEXT();
            result = vkCreateDebugReportCallbackEXT(
                    vulkanInstance,
                    debugReportCallbackCreateInfoEXT,
                    (VkAllocationCallbacks)null,
                    debugCallbackHandle);
            if (result != VkResult.VK_SUCCESS)
            {
                debugCallbackHandle = null;
                throw new AssertionError("Failed to create vkCreateDebugReportCallbackEXT: " + vkResultToString(result));
            }
            
            log.debug("Callback handle is {}.", String.format("%x", debugCallbackHandle.getHandleValue()));
            log.debug("Created debug callback stuff.");
        }
    }
    
//    private void initGLFWWindow()
//    {
//        log.trace("Attempting to initialize glfw.");
//        if (glfwInit() == false)
//        {
//            throw new RuntimeException("Failed to initialize GLFW");
//        }
//        log.debug("glfw initialized.");
//        
//        log.trace("Checking for Vulkan support.");
//        if (glfwVulkanSupported() == false)
//        {
//            throw new AssertionError("GLFW failed to find the Vulkan loader");
//        }
//        log.debug("Vulkan support is available.");
//
//        // Create GLFW window
//        glfwDefaultWindowHints();
//        
//        // Tell GLFW not to create an OpenGL context
//        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
//        
//        /*
//         *  Set the window so it is not visible until shown
//         *  (glfwShowWindow())
//         */
//        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
//        
//        // For now, disable window resize
//        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
//        
//        log.trace("Attemping to create the GLFW window");
//        windowHandle = glfwCreateWindow(windowWidth, windowHeight, "Vulkan Tutorial", NULL, NULL);
//        
//        if (windowHandle == 0)
//        {
//            log.error("window creation was not successful.");
//
//            throw new AssertionError("GLFW window creation was not successful.");
//        }
//        else
//            log.debug("GLFW window creation was successful.");
//    }
//    
    public void cleanup()
    {
        log.trace("Attemping to clean up Vulkan.");
        cleanupVulkan();
        log.debug("Cleaned up Vulkan.");
        
//        log.trace("Attemping to clean up GLFW.");
//        cleanupGLFW();
//        log.debug("Cleaned up GLFW.");

        log.trace("Attemping to clean up Wayland.");
        cleanupWaylandWindow();
        log.debug("Cleaned up Wayland.");
    }
    
//    private void cleanupGLFW()
//    {
//        glfwDestroyWindow(windowHandle);
//        
//        glfwTerminate();
//    }
//    
    private void cleanupSwapchain(boolean shuttingDown)
    {
        log.trace("Attempting to destroy the framebuffers.");
        for (VkFramebuffer handle : swapchainFramebufferReferences)
        {
            vkDestroyFramebuffer(vulkanLogicalDevice, handle, null);
        }
        swapchainFramebufferReferences.clear();
        log.debug("Destroyed the framebuffers.");
        
        log.trace("Attempting to free the command buffers.");
        vkFreeCommandBuffers(vulkanLogicalDevice, vulkanGraphicsCommandPoolHandle, swapchainRenderCommandBuffers);
        swapchainRenderCommandBuffers.clear();
        log.trace("Freed the command buffers.");
        
        log.trace("Attempting to destroy the graphics pipeline.");
        vkDestroyPipeline(vulkanLogicalDevice, vulkanPipelineHandle, null);
        log.trace("Destroyed the graphics pipeline.");
        
        log.trace("Attempting to destroy the pipeline layout.");
        vkDestroyPipelineLayout(vulkanLogicalDevice, vulkanPipelineLayoutHandle, null);
        log.debug("Destroyed the pipeline layout.");
        
        log.trace("Attempting to destroy the render pass.");
        vkDestroyRenderPass(vulkanLogicalDevice, vulkanRenderPassHandle, null);
        log.debug("Destroyed the render pass.");
        
        /*
         * It seems like the images do not need to get destroyed, but,
         * the array list of them needs to be cleared.
         */
//        log.trace("Attempting to destroy the swapchain image.");
//        for (Long imageReference : swapchainImageReferences)
//        {
//            vkDestroyImage(vulkanLogicalDevice, imageReference.longValue(), null);
//        }
        swapchainImageReferences.clear();
//        log.trace("Destroyed the swapchain images.");
        
        
        log.trace("Attempting to destroy the swapchain image views.");
        for (VkImageView imageViewReference : swapchainImageViewReferences)
        {
            vkDestroyImageView(vulkanLogicalDevice, imageViewReference, null);
        }
        swapchainImageViewReferences.clear();
        log.trace("Destroyed the swapchain image views.");
        
        if (shuttingDown == true)
        {
            log.trace("Attempting to destroy the swapchain.");
            vkDestroySwapchainKHR(vulkanLogicalDevice, vulkanSwapchainHandle, null);
            log.trace("Destroyed the swapchain.");
        }
    }
    
    private void cleanupVulkan()
    {
        log.trace("Attemping to clean up Vulkan objects.");
        // Create and submit post present barrier
        
        cleanupSwapchain(true);
        
        log.trace("Attempting to destroy the descriptor pool.");
        vkDestroyDescriptorPool(vulkanLogicalDevice, descriptorPoolHandle, null);
        log.debug("Destroyed the descriptor pool.");

        log.trace("Attempting to destroy the layout descriptor set.");
        vkDestroyDescriptorSetLayout(vulkanLogicalDevice, descriptorSetLayoutHandle, null);
        log.trace("Destroyed the layout descriptor set.");
        
        for (BufferInformation uniformBufferInfo : uniformBufferInformationCollection)
        {
            log.trace("Attempting to destroy the uniform buffer object.");
            vkDestroyBuffer(vulkanLogicalDevice, uniformBufferInfo.bufferHandle, null);
            log.debug("Destroyed the uniform buffer object.");
            
            log.trace("Releasing the iuniform buffer object memory.");
            vkFreeMemory(vulkanLogicalDevice, uniformBufferInfo.bufferMemoryHandle, null);
            log.debug("Released the iuniform buffer object memory.");
        }
        
        uniformBufferInformationCollection.clear();
        uniformBufferInformationCollection = null;
        
        log.trace("Attempting to destroy the index buffer.");
        vkDestroyBuffer(vulkanLogicalDevice, indexBufferInformation.bufferHandle, null);
        log.debug("Destroyed the index buffer.");
        
        log.trace("Releasing the index buffer memory.");
        vkFreeMemory(vulkanLogicalDevice, indexBufferInformation.bufferMemoryHandle, null);
        log.debug("Released the index buffer memory.");

        log.trace("Attempting to destroy the vertex buffer.");
        vkDestroyBuffer(vulkanLogicalDevice, vertexBufferInformation.bufferHandle, null);
        log.debug("Destroyed the vertex buffer.");

        log.trace("Releasing the vertex buffer memory.");
        vkFreeMemory(vulkanLogicalDevice, vertexBufferInformation.bufferMemoryHandle, null);
        log.debug("Released the vertex buffer memory.");
        
        log.trace("Waiting for presentation queue to go idle.");
        vkQueueWaitIdle(vulkanPresentationCommandsQueue);
        log.debug("Presentation queue is idle.");

        log.trace("Attempting to destroy the imageAvailableSemaphores semaphores.");
        for (VkSemaphore handle : imageAvailableSemaphoreHandles)
        {
            vkDestroySemaphore(vulkanLogicalDevice, handle, null);
        }
        log.debug("Destroyed the imageAvailableSemaphores semaphores.");

        log.trace("Attempting to destroy the renderFinishedSemaphores semaphores.");
        for (VkSemaphore handle : renderFinishedSemaphoreHandles)
        {
            vkDestroySemaphore(vulkanLogicalDevice, handle, null);
        }
        log.debug("Destroyed the renderFinishedSemaphores semaphores.");

        log.trace("Attempting to destroy the inFlightFences.");
        for (VkFence handle : inFlightFenceHandles)
        {
            vkDestroyFence(vulkanLogicalDevice, handle, null);
        }
        log.debug("Destroyed the inFlightFences.");
        
        log.trace("Attempting to destroy the command pool.");
        vkDestroyCommandPool(vulkanLogicalDevice, vulkanGraphicsCommandPoolHandle, null);
        log.trace("Destroyed the command pool.");
        
        log.trace("Attempting to destroy window surface.");
        vkDestroySurfaceKHR(vulkanInstance, vulkanSurface, null);
        log.debug("Destroyed window surface.");
        
        log.trace("Attemping to destroy the logical Vulkan device.");
        vkDestroyDevice(vulkanLogicalDevice, null);
        log.trace("Vulkan logical device destroyed.");
        
        log.trace("Attemping to destroy debug callback.");
        vkDestroyDebugReportCallbackEXT(vulkanInstance, debugCallbackHandle, null);
        log.trace("Destroyed debug callback.");

        log.trace("Destoying Vulkan Instance. {}");
        vkDestroyInstance(vulkanInstance, null);
        log.debug("Vulkan Instance destroyed.");
    }
    
    private void mainLoop()
    {
//        glfwShowWindow(windowHandle);

        log.debug("Entering main loop.");
        int i = 0;
        while(i++ < 100)
        {
//      while(glfwWindowShouldClose(windowHandle) == false)
//        {
//            glfwPollEvents();
        
            drawFrame();
        }
        
        log.debug("The main loop has terminated.");
        
        // Wait for things to calm down.
        vkDeviceWaitIdle(vulkanLogicalDevice);
    }

    /**
     * FIXME
     * 
     * This function recreates the fence for the current frame.
     * <p>
     * This is needed in order to "reset" the signaled bit to
     * true so that the flow will not hang in drawFrame after
     * a resize.  A better method would be to be able to set
     * the fence as signaled, but, I do not know how to do that
     * yet.
     * @param currentFrame
     */
    private void reCreateFence(int currentFrame)
    {
        vkDestroyFence(vulkanLogicalDevice, inFlightFenceHandles.get(currentFrame), null);

        VkFenceCreateInfo fenceCreateInfo = new VkFenceCreateInfo();
        fenceCreateInfo.setFlags(EnumSet.of(VkFenceCreateFlagBits.VK_FENCE_CREATE_SIGNALED_BIT));
        VkFence vkFence = new VkFence();
        VkResult result = vkCreateFence(vulkanLogicalDevice, fenceCreateInfo, null, vkFence);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create renderFinishedSemaphoreHandle: " + vkResultToString(result));
        }
        
        inFlightFenceHandles.set(currentFrame, vkFence);
    }
    
    private void drawFrame()
    {
        try
        {
            /*
             * This is here to slow the frame rate down a little.
             */
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        int indexToImage = -1;
        VkResult result = VkResult.VK_RESULT_MAX_ENUM;
        
        Collection<VkFence> fenceCollection = new LinkedList<VkFence>();
        fenceCollection.add(inFlightFenceHandles.get(currentFrame));
        
        log.trace("Frame {} Waiting on fence {}", currentFrame, inFlightFenceHandles.get(currentFrame));
        vkWaitForFences(vulkanLogicalDevice, fenceCollection, true, UINT64_MAX);
        vkResetFences(vulkanLogicalDevice, fenceCollection);
        log.trace("Frame {} Fence {} released.", currentFrame, inFlightFenceHandles.get(currentFrame));
        
        IntReturnValue imageIndex = new IntReturnValue(); 
        result = vkAcquireNextImageKHR(
                        vulkanLogicalDevice,
                        vulkanSwapchainHandle,
                        UINT64_MAX,
                        imageAvailableSemaphoreHandles.get(currentFrame),
                        null,
                        imageIndex);
        if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR)
        {
            log.debug("Swapchain out of date...recreating swapchain...fence is {} image index is {}.",
                    inFlightFenceHandles.get(currentFrame), imageIndex.getValue());
            
            reCreateSwapchain();
            
            /*
             * Since I do not know how to signal a fence I need to destroy
             * and recreate it in the signaled state so that the
             * vkWaitForFences above does not hang forever since vkQueueSubmit
             * was never called in the case of a resize event.
             */
            reCreateFence(currentFrame);
            
            return;
        }
        else if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to acquire next swapchain image: " + vkResultToString(result));
        }
        
        indexToImage = imageIndex.getValue();
        log.debug("Swapchain image index is {}.", indexToImage);
        
        updateUniformBuffer(indexToImage);

        Collection<VkSemaphore> waitSemaphoreCollection = new LinkedList<VkSemaphore>();
        waitSemaphoreCollection.add(imageAvailableSemaphoreHandles.get(currentFrame));
        
        Collection<VkCommandBuffer> commandBufferCollection = new LinkedList<VkCommandBuffer>();
        commandBufferCollection.add(swapchainRenderCommandBuffers.get(indexToImage));
        
        Collection<VkSemaphore> signalSemaphoreCollection = new LinkedList<VkSemaphore>();
        signalSemaphoreCollection.add(renderFinishedSemaphoreHandles.get(currentFrame));
        
        Collection<EnumSet<VkPipelineStageFlagBits>> waitDstStageMaskCollection = new LinkedList<EnumSet<VkPipelineStageFlagBits>>();
        waitDstStageMaskCollection.add(EnumSet.of(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT));
        
        VkSubmitInfo submitInfo = new VkSubmitInfo();
        submitInfo.setWaitSemaphores(waitSemaphoreCollection);
        submitInfo.setWaitDstStageMask(waitDstStageMaskCollection);
        submitInfo.setCommandBuffers(commandBufferCollection);
        submitInfo.setSignalSemaphores(signalSemaphoreCollection);
        
        Collection<VkSubmitInfo> submitInfoCollection = new LinkedList<VkSubmitInfo>();
        submitInfoCollection.add(submitInfo);
        
        result = vkQueueSubmit(vulkanGraphicsCommandsQueue, submitInfoCollection, inFlightFenceHandles.get(currentFrame));
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to submit command to vulkanGraphicsCommandsQueue: " + vkResultToString(result));
        }
        
        Collection<VkSwapchainKHR> swapchainHandleCollection = new LinkedList<VkSwapchainKHR>();
        swapchainHandleCollection.add(vulkanSwapchainHandle);
        
//        Collection<VkResult> resultsCollection = new LinkedList<VkResult>();
        Collection<VkResult> resultsCollection = null;
        
        int[] imageIndexArray = new int[1];
        imageIndexArray[0] = indexToImage;
        
        VkPresentInfoKHR presentInfoKHR = new VkPresentInfoKHR();
        presentInfoKHR.setWaitSemaphores(signalSemaphoreCollection);
        presentInfoKHR.setSwapchains(swapchainHandleCollection);
        presentInfoKHR.setImageIndices(imageIndexArray);
        presentInfoKHR.setResults(resultsCollection);
        
        result = vkQueuePresentKHR(vulkanPresentationCommandsQueue, presentInfoKHR);
        if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR || result == VkResult.VK_SUBOPTIMAL_KHR)
        {
            log.debug("Swapchain out of date or suboptimal...recreating.");
            reCreateSwapchain();
        }
        else if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to submit command to vulkanPresentationCommandsQueue: " + vkResultToString(result));
        }
        
//        if (resultsCollection != null)
//        {
//            log.debug("ResultsCollection size is {}.", resultsCollection.size());
//            for (VkResult aResult : resultsCollection)
//            {
//                log.debug("Result is {}", aResult.toString());
//            }
//        }
        
        currentFrame++;
        currentFrame %= MAX_FRAMES_IN_FLIGHT;
        log.trace("Current frame is {}", currentFrame);
    }

    private class SwapchainSupportDetails
    {
        VkSurfaceCapabilitiesKHR surfaceCapabilities;
        Collection<VkSurfaceFormatKHR> surfaceFormats;
        Collection<VkPresentModeKHR> presentationModes;

        SwapchainSupportDetails(VkSurfaceCapabilitiesKHR surfaceCapabilities,
                                Collection<VkSurfaceFormatKHR> surfaceFormats,
                                Collection<VkPresentModeKHR> presentationModes)
        {
            this.surfaceCapabilities = surfaceCapabilities;
            this.surfaceFormats = surfaceFormats;
            this.presentationModes = presentationModes;
        }
        
        void clean()
        {
            surfaceCapabilities = null;
            surfaceFormats.clear();
            surfaceFormats = null;
            presentationModes.clear();
            presentationModes = null;
        }
    }
    
    private static VkShaderModule loadShader(String fileName, VkDevice device) throws IOException
    {
        RandomAccessFile theFile = new RandomAccessFile(fileName, "r");
        FileChannel inputChannel = theFile.getChannel();
        
        int fileLength = (int)inputChannel.size();
        
//        ByteBuffer shaderCode = ByteBuffer.allocateDirect(fileLength);
        ByteBuffer shaderCode = ByteBuffer.allocate(fileLength);
        
        inputChannel.read(shaderCode);
        
        shaderCode.flip();
        
        inputChannel.close();
        theFile.close();
        
        VkShaderModule shaderModule = new VkShaderModule();
        VkShaderModuleCreateInfo shaderModuleCreateInfo = new VkShaderModuleCreateInfo();
        shaderModuleCreateInfo.setCode(shaderCode, fileLength);
        
        VkResult result = vkCreateShaderModule(device, shaderModuleCreateInfo, null, shaderModule);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to create shader module: " + vkResultToString(result));
        }
      
        return shaderModule;
    }
    
    private void updateUniformBuffer(int indexToCurrentImage)
    {
        /*
         * Compute the time in ms between when we started and now.
         */
        long deltaTimeInms = Duration.between(startTime, Instant.now()).toMillis();
        
        /*
         * We are going for 90 degrees per second or 1 revolution in 4 seconds.
         */
        
        deltaTimeInms %= 4000;
        
        double angleInDegrees = ((double)deltaTimeInms/4000.0 * 360.0);
        log.trace("Computed angle is {} degrees.", angleInDegrees);
        
        float angleInRadians = (float)Math.toRadians(angleInDegrees);
        
        uniformBufferObject.model = new Matrix4f().identity().rotate(angleInRadians, new Vector3f(0.0f, 0.0f, 1.0f));
        uniformBufferObject.view = new Matrix4f().lookAt(
                2.0f, 2.0f, 2.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f);
        uniformBufferObject.projection = new Matrix4f()
                .perspective((float)Math.toRadians(45.0f),
                        swapchainExtentUsed.getWidth() / 
                        (float)swapchainExtentUsed.getHeight(), 0.1f, 10.0f);
        
        /*
         * We need to flip the y axis because of the difference between OpenGL and Vulkan.
         */
        uniformBufferObject.projection.m11(uniformBufferObject.projection.m11() * -1);
        
        log.trace("UniformBufferObject {} total size is {}.", uniformBufferInformationCollection.get(indexToCurrentImage).bufferHandle, uniformBufferObject.sizeInBytes());
//        ByteBuffer dataBuffer = org.lwjgl.system.MemoryUtil.memAlloc(uniformBufferObject.sizeInBytes());
        
//        FloatBuffer floatBuffer = dataBuffer.asFloatBuffer();
        FloatBuffer floatBuffer = FloatBuffer.allocate(16 * 3);

        float modelData[] = new float[16];
        uniformBufferObject.model.get(modelData);
        
        float viewData[] = new float[16];
        uniformBufferObject.view.get(viewData);
        
        float projectionData[] = new float[16];
        uniformBufferObject.projection.get(projectionData);
        
        for (int i = 0; i < 16; i++)
        {
            floatBuffer.put(modelData[i]);
        }
        
        for (int i = 0; i < 16; i++)
        {
            floatBuffer.put(viewData[i]);
        }
        
        for (int i = 0; i < 16; i++)
        {
            floatBuffer.put(projectionData[i]);
        }
        
        MappedMemoryPointer pointerToMappedMemory = new MappedMemoryPointer();
        VkResult result = vkMapMemory(
                vulkanLogicalDevice,
                uniformBufferInformationCollection.get(indexToCurrentImage).bufferMemoryHandle,
                0,
                uniformBufferObject.sizeInBytes(),
                EnumSet.noneOf(VkMemoryMapFlagBits.class),
                pointerToMappedMemory);
        if (result != VkResult.VK_SUCCESS)
        {
            throw new AssertionError("Failed to map memory: " + vkResultToString(result));
        }
        
        pushDataToVirtualMemory(floatBuffer, pointerToMappedMemory);
        
        vkUnmapMemory(vulkanLogicalDevice, uniformBufferInformationCollection.get(indexToCurrentImage).bufferMemoryHandle);
    }
}
