package soupbubbles.pesquerasnethermod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.MathHelper;

public class ModelBee extends ModelBiped
{
    private final ModelRenderer armLeftLower;
    private final ModelRenderer armRightLower;
    
    private final ModelRenderer antennaRight;
    private final ModelRenderer antennaLeft;
    private final ModelRenderer wingfLeft1;
    private final ModelRenderer wingLeft2;
    private final ModelRenderer wingLeft3;
    private final ModelRenderer wingRight1;
    private final ModelRenderer wingRight2;
    private final ModelRenderer wingRight3;

    public ModelBee()
    {
        textureWidth = 64;
        textureHeight = 64;

        bipedBody = new ModelRenderer(this, 0, 0);
        bipedBody.addBox(-3F, -11F, -2F, 6, 11, 3);
        bipedBody.setRotationPoint(0F, 17F, 0F);
        bipedBody.setTextureSize(64, 64);
        bipedBody.mirror = true;
        setRotation(bipedBody, 0F, 0F, 0F);
        bipedRightLeg = new ModelRenderer(this, 30, 0);
        bipedRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
        bipedRightLeg.setRotationPoint(-1.5F, 17F, -0.5F);
        bipedRightLeg.setTextureSize(64, 64);
        bipedRightLeg.mirror = true;
        setRotation(bipedRightLeg, 0F, 0F, 0F);
        bipedLeftLeg = new ModelRenderer(this, 18, 0);
        bipedLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
        bipedLeftLeg.setRotationPoint(1.5F, 17F, -0.5F);
        bipedLeftLeg.setTextureSize(64, 64);
        bipedLeftLeg.mirror = true;
        setRotation(bipedLeftLeg, 0F, 0F, 0.0174533F);
        bipedLeftArm = new ModelRenderer(this, 18, 10);
        bipedLeftArm.addBox(0F, -1F, -1F, 2, 6, 2);
        bipedLeftArm.setRotationPoint(3F, 11F, -0.5F);
        bipedLeftArm.setTextureSize(64, 64);
        bipedLeftArm.mirror = true;
        setRotation(bipedLeftArm, -1.570796F, 0F, 0F);
        armLeftLower = new ModelRenderer(this, 18, 18);
        armLeftLower.addBox(0F, -1F, -1F, 2, 6, 2);
        armLeftLower.setRotationPoint(3F, 15F, -0.5F);
        armLeftLower.setTextureSize(64, 64);
        armLeftLower.mirror = true;
        setRotation(armLeftLower, -1.570796F, 0F, 0F);
        bipedRightArm = new ModelRenderer(this, 26, 10);
        bipedRightArm.addBox(-2F, -1F, -1F, 2, 6, 2);
        bipedRightArm.setRotationPoint(-3F, 11F, -0.5F);
        bipedRightArm.setTextureSize(64, 64);
        bipedRightArm.mirror = true;
        setRotation(bipedRightArm, -1.570796F, 0F, 0F);
        armRightLower = new ModelRenderer(this, 26, 18);
        armRightLower.addBox(-2F, -1F, -1F, 2, 6, 2);
        armRightLower.setRotationPoint(-3F, 15F, -0.5F);
        armRightLower.setTextureSize(64, 64);
        armRightLower.mirror = true;
        setRotation(armRightLower, -1.570796F, 0F, 0F);
        bipedHead = new ModelRenderer(this, 0, 14);
        bipedHead.addBox(-3F, -3F, -2F, 6, 8, 3);
        bipedHead.setRotationPoint(0F, 7F, -1F);
        bipedHead.setTextureSize(64, 64);
        bipedHead.mirror = true;
        setRotation(bipedHead, -0.4363323F, 0F, 0F);
        antennaRight = new ModelRenderer(this, 0, 25);
        antennaRight.addBox(-3F, -9F, -2F, 1, 6, 1);
        antennaRight.setRotationPoint(0F, 7F, -1F);
        antennaRight.setTextureSize(64, 64);
        antennaRight.mirror = true;
        setRotation(antennaRight, -0.4363323F, 0F, 0F);
        antennaLeft = new ModelRenderer(this, 4, 25);
        antennaLeft.addBox(2F, -9F, -2F, 1, 6, 1);
        antennaLeft.setRotationPoint(0F, 7F, -1F);
        antennaLeft.setTextureSize(64, 64);
        antennaLeft.mirror = true;
        setRotation(antennaLeft, -0.4363323F, 0F, 0F);
        wingfLeft1 = new ModelRenderer(this, 0, 60);
        wingfLeft1.addBox(-0.5333334F, 0F, 0F, 1, 4, 0);
        wingfLeft1.setRotationPoint(1F, 8F, 1F);
        wingfLeft1.setTextureSize(64, 64);
        wingfLeft1.mirror = true;
        setRotation(wingfLeft1, 0.1570796F, 0F, 0F);
        wingRight1 = new ModelRenderer(this, 0, 56);
        wingRight1.addBox(-0.5F, 0F, 0F, 1, 4, 0);
        wingRight1.setRotationPoint(-1F, 8F, 1F);
        wingRight1.setTextureSize(64, 64);
        wingRight1.mirror = true;
        setRotation(wingRight1, 0.1570796F, 0F, 0F);
        wingLeft3 = new ModelRenderer(this, 6, 59);
        wingLeft3.addBox(0.5F, 4F, 0F, 2, 5, 0);
        wingLeft3.setRotationPoint(1F, 8F, 1F);
        wingLeft3.setTextureSize(64, 64);
        wingLeft3.mirror = true;
        setRotation(wingLeft3, 0.1570796F, 0F, 0F);
        wingLeft2 = new ModelRenderer(this, 2, 57);
        wingLeft2.addBox(-0.5F, 2F, 0F, 2, 7, 0);
        wingLeft2.setRotationPoint(1F, 8F, 1F);
        wingLeft2.setTextureSize(64, 64);
        wingLeft2.mirror = true;
        setRotation(wingLeft2, 0.1570796F, 0F, 0F);
        wingRight2 = new ModelRenderer(this, 2, 50);
        wingRight2.addBox(-1.5F, 2F, 0F, 2, 7, 0);
        wingRight2.setRotationPoint(-1F, 8F, 1F);
        wingRight2.setTextureSize(64, 64);
        wingRight2.mirror = true;
        setRotation(wingRight2, 0.1570796F, 0F, 0F);
        wingRight3 = new ModelRenderer(this, 6, 54);
        wingRight3.addBox(-2.5F, 4F, 0F, 2, 5, 0);
        wingRight3.setRotationPoint(-1F, 8F, 1F);
        wingRight3.setTextureSize(64, 64);
        wingRight3.mirror = true;
        setRotation(wingRight3, 0.1570796F, 0F, 0F);
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        
        armLeftLower.render(scale);
        armRightLower.render(scale);
        
        antennaRight.render(scale);
        antennaLeft.render(scale);
        
        wingfLeft1.render(scale);
        wingRight1.render(scale);
        wingLeft3.render(scale);
        wingLeft2.render(scale);
        wingRight2.render(scale);
        wingRight3.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity)
    {
        this.bipedHead.rotateAngleZ = -1.570796F;
        
        this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

        this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
        
        this.bipedHead.rotationPointY = 0.0F;
    }

}
