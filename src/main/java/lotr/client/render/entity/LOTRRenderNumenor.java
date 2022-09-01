package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.common.entity.npc.LOTREntityKhand;
import lotr.common.entity.npc.LOTREntityNumenor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderNumenor extends LOTRRenderBiped {
	   private static LOTRRandomSkins numenorSkinsMale;
	    private static LOTRRandomSkins numenorSkinsFemale;
	    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

	    public LOTRRenderNumenor() {
	        super(new LOTRModelHuman(), 0.5f);
	        this.setRenderPassModel(this.outfitModel);
	        numenorSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/gondor_male");
	        numenorSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/gondor/gondor_female");
	    }

	    @Override
	    public ResourceLocation getEntityTexture(Entity entity) {
	    	LOTREntityNumenor numenor = (LOTREntityNumenor) entity;
	        if(numenor.familyInfo.isMale()) {
	            return numenorSkinsMale.getRandomSkin(numenor);
	        }
	        return numenorSkinsFemale.getRandomSkin(numenor);
	    }
	}

