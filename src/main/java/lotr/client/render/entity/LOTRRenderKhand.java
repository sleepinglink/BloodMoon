package lotr.client.render.entity;

import lotr.client.model.LOTRModelHuman;
import lotr.common.entity.npc.LOTREntityKhand;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderKhand extends LOTRRenderBiped{
    private static LOTRRandomSkins khandlingSkinsMale;
    private static LOTRRandomSkins khandlingSkinsFemale;
    protected ModelBiped outfitModel = new LOTRModelHuman(0.6f, false);

    public LOTRRenderKhand() {
        super(new LOTRModelHuman(), 0.5f);
        this.setRenderPassModel(this.outfitModel);
        khandlingSkinsMale = LOTRRandomSkins.loadSkinsList("lotr:mob/khand/khand_male");
        khandlingSkinsFemale = LOTRRandomSkins.loadSkinsList("lotr:mob/khand/khand_female");
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
    	LOTREntityKhand khanding = (LOTREntityKhand) entity;
        if(khanding.familyInfo.isMale()) {
            return khandlingSkinsMale.getRandomSkin(khanding);
        }
        return khandlingSkinsFemale.getRandomSkin(khanding);
    }
}
