package soupbubbles.pesquerasnethermod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLarva extends ModelBase
{
    // Body size: Width, height, length
    private static final int[][] BODY_SIZES = new int[][] {{4, 3, 4}, {5, 4, 6}, {4, 3, 3}, {3, 2, 3}};
    private static final int[][] BODY_TEXS = new int[][] {{0, 0}, {0, 7}, {0, 17}, {0, 23}};
    private static final int BODY_COUNT = BODY_SIZES.length;

    private final ModelRenderer[] bodyParts;

    private final ModelRenderer[] eyes = new ModelRenderer[2];
    private final ModelRenderer[] horns = new ModelRenderer[2];

    public ModelLarva()
    {
        bodyParts = new ModelRenderer[BODY_COUNT];
        float f = -3.5F;

        for (int i = 0; i < bodyParts.length; ++i)
        {
            bodyParts[i] = new ModelRenderer(this, BODY_TEXS[i][0], BODY_TEXS[i][1]);
            bodyParts[i].addBox((float) BODY_SIZES[i][0] * -0.5F, 0.0F, (float) BODY_SIZES[i][2] * -0.5F, BODY_SIZES[i][0], BODY_SIZES[i][1], BODY_SIZES[i][2]);
            bodyParts[i].setRotationPoint(0.0F, (float) (24 - BODY_SIZES[i][1]), f);

            if (i < bodyParts.length - 1)
            {
                f += (float) (BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
            }
        }
        
        bodyParts[3].offsetZ -= 0.02F;

        for (int j = 0; j < eyes.length; ++j)
        {
            eyes[j] = new ModelRenderer(this, 0, 28);
            eyes[j].addBox((4.0F * j) - 2.5F, 0.3F, -1.5F, 1, 2, 2);
            eyes[j].setRotationPoint(bodyParts[0].rotationPointX, bodyParts[0].rotationPointY, bodyParts[0].rotationPointZ);
            
            horns[j] = new ModelRenderer(this, 6, 28);
            horns[j].addBox((3.0F * j) - 2.0F, -1.7F, 0.3F, 1, 3, 1);
            horns[j].setRotationPoint(bodyParts[0].rotationPointX, bodyParts[0].rotationPointY, bodyParts[0].rotationPointZ);
        }

        setRotation();
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        for (ModelRenderer modelrenderer : bodyParts)
        {
            modelrenderer.render(scale);
        }

        for (int j = 0; j < eyes.length; ++j)
        {
            eyes[j].render(scale);
            horns[j].render(scale);
        }
    }

    public void setRotation()
    {
        bodyParts[0].rotateAngleX = 0.2F;
        eyes[0].rotateAngleX = 0.2F;
        eyes[1].rotateAngleX = 0.2F;
        
        horns[0].rotateAngleX = -0.5F;
        horns[1].rotateAngleX = -0.5F;
 
        bodyParts[2].rotateAngleX = -0.15F;
        bodyParts[3].rotateAngleX = -0.01F;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        for (int i = 0; i < bodyParts.length; ++i)
        {
            bodyParts[i].rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.01F * (float) (1 + Math.abs(i - 2));
            bodyParts[i].rotationPointX = MathHelper.sin(ageInTicks * 0.9F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.1F * (float) Math.abs(i - 2);
        }

        for (int j = 0; j < eyes.length; ++j)
        {
            eyes[j].rotateAngleY = bodyParts[0].rotateAngleY;
            eyes[j].rotationPointX = bodyParts[0].rotationPointX;
            horns[j].rotateAngleY = bodyParts[0].rotateAngleY + -1.0F * ((((float) j * 4.0F) - 2.0F) / 10.0F);
            horns[j].rotationPointX = bodyParts[0].rotationPointX;
        }
    }
}
