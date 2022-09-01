package lotr.client.model;


import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LOTRModelNumenorHelmet extends LOTRModelGondorHelmet {
	public   LOTRModelNumenorHelmet() {
        this(0.0f);
    }
    public LOTRModelNumenorHelmet(float f) {
    	 super(f);
         this.bipedHead = new ModelRenderer(this, 0, 0);
         this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
         this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
         this.bipedHead.setTextureOffset(0, 16).addBox(-1.5f, -9.0f, -3.5f, 3, 1, 7, f);
         this.bipedHead.setTextureOffset(20, 16).addBox(-0.5f, -10.0f, -3.5f, 1, 1, 7, f);
         this.bipedHead.setTextureOffset(24, 0).addBox(-1.5f, -10.5f - f, -4.5f - f, 3, 4, 1, 0.0f);
         this.bipedHead.setTextureOffset(24, 5).addBox(-0.5f, -11.5f - f, -4.5f - f, 1, 1, 1, 0.0f);
         this.bipedHead.setTextureOffset(28, 5).addBox(-0.5f, -6.5f - f, -4.5f - f, 1, 1, 1, 0.0f);
         this.bipedHead.setTextureOffset(32, 0).addBox(-1.5f, -9.5f - f, 3.5f + f, 3, 3, 1, 0.0f);
         this.bipedHead.setTextureOffset(32, 4).addBox(-0.5f, -10.5f - f, 3.5f + f, 1, 1, 1, 0.0f);
         this.bipedHead.setTextureOffset(36, 4).addBox(-0.5f, -6.5f - f, 3.5f + f, 1, 1, 1, 0.0f);
         this.bipedHeadwear.cubeList.clear();
         this.bipedBody.cubeList.clear();
         this.bipedRightArm.cubeList.clear();
         this.bipedLeftArm.cubeList.clear();
         this.bipedRightLeg.cubeList.clear();
         this.bipedLeftLeg.cubeList.clear();
         super.bipedBody.cubeList.clear();
         super.bipedRightArm.cubeList.clear();
         super.bipedLeftArm.cubeList.clear();
         super.bipedRightLeg.cubeList.clear();
         super.bipedLeftLeg.cubeList.clear();
         ModelRenderer wingLeft = new ModelRenderer(this, 0, 16);
         wingLeft.setRotationPoint(-3.5F - f, -2.0F - f, -5.0F);
         wingLeft.addBox(-6.0F, -6.0F, 0.0F, 6, 16, 0, 0.0F);
         wingLeft.rotateAngleY = (float)Math.toRadians(-20.0D);
         wingLeft.rotateAngleX = (float)Math.toRadians(-30.0D);
         super.bipedHead.addChild(wingLeft);
         ModelRenderer wingRight = new ModelRenderer(this, 0, 16);
         wingRight.mirror = true;
         wingRight.setRotationPoint(3.5F + f, -2.0F - f, -5.0F);
         wingRight.addBox(0.0F, -6.0F, 0.0F, 6, 16, 0, 0.0F);
         wingRight.rotateAngleY = (float)Math.toRadians(20.0D);
         wingRight.rotateAngleX = (float)Math.toRadians(-30.0D);
         super.bipedHead.addChild(wingRight);
      }

      public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
         super.render(entity, f, f1, f2, f3, f4, f5);
         this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      }

      public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
         super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      }
     
    }
   