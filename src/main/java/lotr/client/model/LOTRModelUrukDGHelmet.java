package lotr.client.model;


import net.minecraft.client.model.ModelRenderer;

public class LOTRModelUrukDGHelmet extends LOTRModelBiped {
	 public LOTRModelUrukDGHelmet() {
           
		 this(0.0f);
	    }
	  
	        
	    public LOTRModelUrukDGHelmet(float f) {
	        super(f);
	        this.bipedHead = new ModelRenderer(this, 0, 0);
	        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
	        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
	        ModelRenderer hornRight = new ModelRenderer(this, 32, 0);
	        hornRight.setRotationPoint(-f, -f, -f);
	        hornRight.addBox(-7.0f, -12.0f, 0.5f, 3, 8, 0, 0.0f);
	        hornRight.rotateAngleZ = (float) Math.toRadians(6.0);
	        ModelRenderer hornLeft = new ModelRenderer(this, 32, 0);
	        hornLeft.setRotationPoint(f, -f, -f);
	        hornLeft.mirror = true;
	        hornLeft.addBox(4.0f, -12.0f, 0.5f, 3, 8, 0, 0.0f);
	        hornLeft.rotateAngleZ = (float) Math.toRadians(-6.0);
	        this.bipedHead.addChild(hornRight);
	        this.bipedHead.addChild(hornLeft);
	        this.bipedHeadwear.cubeList.clear();
	        this.bipedBody.cubeList.clear();
	        this.bipedRightArm.cubeList.clear();
	        this.bipedLeftArm.cubeList.clear();
	        this.bipedRightLeg.cubeList.clear();
	        this.bipedLeftLeg.cubeList.clear();
	        
	       
	        this.bipedHead = new ModelRenderer(this, 0, 0);
	        this.bipedHead.setRotationPoint(0.0f, 0.0f, 0.0f);
	        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
	        ModelRenderer wingLeft = new ModelRenderer(this, 33, 0);
	        wingLeft.setRotationPoint(-4.0f - f, -8.0f - f, 0.0f);
	        wingLeft.addBox(-6.0f, -6.0f, 0.0f, 6, 16, 0, 0.0f);
	        wingLeft.rotateAngleY = (float) Math.toRadians(25.0);
	        this.bipedHead.addChild(wingLeft);
	        ModelRenderer wingRight = new ModelRenderer(this, 33, 0);
	        wingRight.mirror = true;
	        wingRight.setRotationPoint(4.0f + f, -8.0f - f, 0.0f);
	        wingRight.addBox(0.0f, -6.0f, 0.0f, 6, 16, 0, 0.0f);
	        wingRight.rotateAngleY = (float) Math.toRadians(-25.0);
	        this.bipedHead.addChild(wingRight);
	        this.bipedHeadwear.cubeList.clear();
	        this.bipedBody.cubeList.clear();
	        this.bipedRightArm.cubeList.clear();
	        this.bipedLeftArm.cubeList.clear();
	        this.bipedRightLeg.cubeList.clear();
	        this.bipedLeftLeg.cubeList.clear();
	        
	       
	    }
	    
}
	    

