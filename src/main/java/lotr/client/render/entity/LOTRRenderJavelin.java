package lotr.client.render.entity;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.common.FMLLog;
import lotr.client.model.LOTRModelBoar;
import lotr.common.LOTRMod;
import lotr.common.entity.projectile.LOTREntityJavelin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class LOTRRenderJavelin extends Render {
    private static ModelBase boarModel = new LOTRModelBoar();

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        LOTREntityJavelin spear = (LOTREntityJavelin) entity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glRotatef(spear.prevRotationYaw + (spear.rotationYaw - spear.prevRotationYaw) * f1 - 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(spear.prevRotationPitch + (spear.rotationPitch - spear.prevRotationPitch) * f1, 0.0f, 0.0f, 1.0f);
        GL11.glEnable(32826);
        float f2 = spear.shake - f1;
        if(f2 > 0.0f) {
            float f3 = -MathHelper.sin(f2 * 3.0f) * f2;
            GL11.glRotatef(f3, 0.0f, 0.0f, 1.0f);
        }
        GL11.glRotatef(-135.0f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(0.0f, -1.0f, 0.0f);
        if(LOTRMod.isAprilFools()) {
            this.bindTexture(LOTRRenderWildBoar.boarSkin);
            GL11.glTranslatef(0.0f, -2.5f, 0.0f);
            GL11.glScalef(0.25f, 0.25f, 0.25f);
            boarModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.625f);
        }
        else {
            ItemStack itemstack = spear.getProjectileItem();
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
            if(customRenderer != null) {
                customRenderer.renderItem(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, new Object[2]);
            }
            else {
                FMLLog.severe("Error rendering spear: no custom renderer for " + itemstack.toString(), new Object[0]);
            }
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    static {
        LOTRRenderJavelin.boarModel.isChild = false;
    }
}
