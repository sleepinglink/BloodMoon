package lotr.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class LOTRModelWagonHelmet extends ModelBiped {
	public LOTRModelWagonHelmet() {
	      this(0.0F);
	   
}
   public LOTRModelWagonHelmet(float f) {
      super(f);
      super.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
      super.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
      ModelRenderer tail = new ModelRenderer(this, 0, 32);
      tail.setTextureOffset(3, 22).addBox(0.0F - f, -16.0F, 0.0F, 11, 9, 0, 0.0F);
      tail.rotateAngleY = (float)Math.toRadians(-90.0D);
      ModelRenderer tail1 = new ModelRenderer(this, 0, 32);
      tail1.setTextureOffset(3, 22).addBox(0.0F - f, -16.0F, 0.0F, 11, 9, 0, 0.0F);
      tail1.rotateAngleY = (float)Math.toRadians(-120.0D);
      ModelRenderer tail2 = new ModelRenderer(this, 0, 32);
      tail2.setTextureOffset(3, 22).addBox(0.0F - f, -16.0F, 0.0F, 11, 9, 0, 0.0F);
      tail2.rotateAngleY = (float)Math.toRadians(-60.0D);
      super.bipedHead.addChild(tail);
      super.bipedHead.addChild(tail1);
      super.bipedHead.addChild(tail2);
      super.bipedHeadwear.cubeList.clear();
      super.bipedBody.cubeList.clear();
      super.bipedRightArm.cubeList.clear();
      super.bipedLeftArm.cubeList.clear();
      super.bipedRightLeg.cubeList.clear();
      super.bipedLeftLeg.cubeList.clear();
   }
}
