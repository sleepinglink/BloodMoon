package lotr.client.render.entity;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import lotr.client.model.LOTRModelEnt;
import lotr.client.model.LOTRModelTroll;
import lotr.common.entity.item.LOTREntityBossTrophy;
import lotr.common.item.LOTRItemBossTrophy;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LOTRRenderBossTrophy extends Render {
    private static Map<LOTRItemBossTrophy.TrophyType, ResourceLocation> trophyTextures = new HashMap<LOTRItemBossTrophy.TrophyType, ResourceLocation>();
    private static LOTRModelTroll trollModel = new LOTRModelTroll();
    private static LOTRModelEnt entModel = new LOTRModelEnt();

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        LOTREntityBossTrophy trophy = (LOTREntityBossTrophy) entity;
        LOTRItemBossTrophy.TrophyType type = trophy.getTrophyType();
        ResourceLocation r = trophyTextures.get(type);
        if(r == null) {
            r = new ResourceLocation("lotr:item/bossTrophy/" + type.trophyName + ".png");
            trophyTextures.put(type, r);
        }
        return r;
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityBossTrophy trophy = (LOTREntityBossTrophy) entity;
        LOTRItemBossTrophy.TrophyType type = trophy.getTrophyType();
        float modelscale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glScalef(-1.0f, -1.0f, 1.0f);
        float rotation = 0.0f;
        rotation = trophy.isTrophyHanging() ? 180.0f + trophy.getTrophyFacing() * 90.0f : 180.0f - entity.rotationYaw;
        GL11.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
        this.bindEntityTexture(entity);
        if(type == LOTRItemBossTrophy.TrophyType.MOUNTAIN_TROLL_CHIEFTAIN) {
            ModelRenderer head = LOTRRenderBossTrophy.trollModel.head;
            head.setRotationPoint(0.0f, -6.0f, 6.0f);
            GL11.glTranslatef(0.0f, -0.05f, 0.1f);
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.25f, 0.0f, 0.0f);
            GL11.glRotatef(-10.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(15.0f, 0.0f, 1.0f, 0.0f);
            head.render(modelscale);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(0.25f, 0.0f, 0.0f);
            GL11.glRotatef(10.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(-15.0f, 0.0f, 1.0f, 0.0f);
            head.render(modelscale);
            GL11.glPopMatrix();
        }
        if(type == LOTRItemBossTrophy.TrophyType.MALLORN_ENT) {
            ModelRenderer trunk = LOTRRenderBossTrophy.entModel.trunk;
            LOTRRenderBossTrophy.entModel.rightArm.showModel = false;
            LOTRRenderBossTrophy.entModel.leftArm.showModel = false;
            LOTRRenderBossTrophy.entModel.trophyBottomPanel.showModel = true;
            float scale = 0.6f;
            GL11.glTranslatef(0.0f, 34.0f * modelscale * scale, 0.0f);
            if(trophy.isTrophyHanging()) {
                GL11.glTranslatef(0.0f, 0.0f, 3.0f * modelscale / scale);
            }
            GL11.glScalef(scale, scale, scale);
            trunk.render(modelscale);
        }
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
}
