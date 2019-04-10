#version 450
#extension GL_ARB_separate_shader_objects : enable

layout(binding = 0) uniform UniformBufferObject {
    mat4 model;
    mat4 view;
    mat4 projection;
} uniformBufferObject;

layout(location = 0) in vec2 inputPosition;
layout(location = 1) in vec3 inputColor;
layout(location = 2) in vec2 inputTextureCoordinate;

layout(location = 0) out vec3 fragmentColor;
layout(location = 1) out vec2 fragmentTextureCoordinate;


void main() {
    gl_Position = uniformBufferObject.projection *
                  uniformBufferObject.view *
                  uniformBufferObject.model * vec4(inputPosition, 0.0, 1.0);
    fragmentColor = inputColor;
    fragmentTextureCoordinate = inputTextureCoordinate;
}
