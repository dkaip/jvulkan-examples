package com.CIMthetics.JVulkanExamples;

import org.joml.Vector2f;
import org.joml.Vector3f;

class Vertex
{
    Vector3f position;
    Vector3f color;
    Vector2f textureCoordinate;
    
    /**
     * 
     * @param position
     * @param color
     * @param textureCoordinate
     */
    Vertex(Vector3f position)
    {
        this.position = position;
        this.color = new Vector3f(1.0f, 1.0f, 1.0f);
    }
    
    static int getPositionOffset()
    {
        return 0;
    }
    
    static int getColorOffset()
    {
        return (3 * 4);
    }
    
    static int getTextureCoordinateOffset()
    {
        return (3 * 4) + (3 * 4);
    }
    
    static int getSizeOfVertexInBytes()
    {
        return (3 * 4) + (3 * 4) + (2 * 4);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result
                + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((textureCoordinate == null) ? 0
                : textureCoordinate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (color == null)
        {
            if (other.color != null)
                return false;
        }
        else if (!color.equals(other.color))
            return false;
        if (position == null)
        {
            if (other.position != null)
                return false;
        }
        else if (!position.equals(other.position))
            return false;
        if (textureCoordinate == null)
        {
            if (other.textureCoordinate != null)
                return false;
        }
        else if (!textureCoordinate.equals(other.textureCoordinate))
            return false;
        return true;
    }

}

