package lotr.client.gui;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import lotr.common.LOTRDimension;
import lotr.common.quest.LOTRMiniQuestWelcome;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class LOTRGuiMenu extends LOTRGuiScreenBase {
    public static final ResourceLocation menuIconsTexture = new ResourceLocation("lotr:gui/menu_icons.png");
    public static Class<? extends LOTRGuiMenuBase> lastMenuScreen = null;

    @Override
    public void initGui() {
        super.initGui();
        LOTRGuiMenu.resetLastMenuScreen();
        int midX = this.width / 2;
        int midY = this.height / 2;
        int buttonGap = 10;
        int buttonSize = 32;
        this.buttonList.add(new LOTRGuiButtonMenu(this, 2, 0, 0, LOTRGuiAchievements.class, StatCollector.translateToLocal("lotr.gui.achievements"), 30));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 3, 0, 0, LOTRGuiMap.class, StatCollector.translateToLocal("lotr.gui.map"), 50));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 4, 0, 0, LOTRGuiFactions.class, StatCollector.translateToLocal("lotr.gui.factions"), 33));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 0, 0, 0, null, "?", -1));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 6, 0, 0, LOTRGuiFellowships.class, StatCollector.translateToLocal("lotr.gui.fellowships"), 25));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 7, 0, 0, LOTRGuiTitles.class, StatCollector.translateToLocal("lotr.gui.titles"), 20));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 5, 0, 0, LOTRGuiShields.class, StatCollector.translateToLocal("lotr.gui.shields"), 31));
        this.buttonList.add(new LOTRGuiButtonMenu(this, 1, 0, 0, LOTRGuiOptions.class, StatCollector.translateToLocal("lotr.gui.options"), 24));
        ArrayList<LOTRGuiButtonMenu> menuButtons = new ArrayList<LOTRGuiButtonMenu>();
        for(Object obj : this.buttonList) {
            if(!(obj instanceof LOTRGuiButtonMenu)) continue;
            LOTRGuiButtonMenu button = (LOTRGuiButtonMenu) obj;
            button.enabled = button.canDisplayMenu();
            menuButtons.add(button);
        }
        int numButtons = menuButtons.size();
        int numTopRowButtons = (numButtons - 1) / 2 + 1;
        int numBtmRowButtons = numButtons - numTopRowButtons;
        int topRowLeft = midX - (numTopRowButtons * buttonSize + (numTopRowButtons - 1) * buttonGap) / 2;
        int btmRowLeft = midX - (numBtmRowButtons * buttonSize + (numBtmRowButtons - 1) * buttonGap) / 2;
        for(int l = 0; l < numButtons; ++l) {
            LOTRGuiButtonMenu button = menuButtons.get(l);
            if(l < numTopRowButtons) {
                button.xPosition = topRowLeft + l * (buttonSize + buttonGap);
                button.yPosition = midY - buttonGap / 2 - buttonSize;
                continue;
            }
            button.xPosition = btmRowLeft + (l - numTopRowButtons) * (buttonSize + buttonGap);
            button.yPosition = midY + buttonGap / 2;
        }
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        String title = StatCollector.translateToLocalFormatted("lotr.gui.menu", LOTRDimension.getCurrentDimension(this.mc.theWorld).getDimensionName());
        this.fontRendererObj.drawStringWithShadow(title, this.width / 2 - this.fontRendererObj.getStringWidth(title) / 2, this.height / 2 - 80, 16777215);
        super.drawScreen(i, j, f);
        for(Object obj : this.buttonList) {
            LOTRGuiButtonMenu button;
            if(!(obj instanceof LOTRGuiButtonMenu) || !(button = (LOTRGuiButtonMenu) obj).func_146115_a() || button.displayString == null) continue;
            float z = this.zLevel;
            int stringWidth = 200;
            this.drawCreativeTabHoveringText(button.displayString, i, j);
            GL11.glDisable(2896);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.zLevel = z;
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        for(Object obj : this.buttonList) {
            if(!(obj instanceof LOTRGuiButtonMenu)) continue;
            LOTRGuiButtonMenu button = (LOTRGuiButtonMenu) obj;
            if(!button.visible || !button.enabled || button.menuKeyCode < 0 || i != button.menuKeyCode) continue;
            this.actionPerformed(button);
            return;
        }
        super.keyTyped(c, i);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        LOTRGuiMenuBase screen;
        LOTRGuiButtonMenu buttonMenu;
        if(button.enabled && button instanceof LOTRGuiButtonMenu && (screen = (buttonMenu = (LOTRGuiButtonMenu) button).openMenu()) != null) {
            this.mc.displayGuiScreen(screen);
            lastMenuScreen = screen.getClass();
            return;
        }
        super.actionPerformed(button);
    }

    public static void resetLastMenuScreen() {
        lastMenuScreen = null;
    }

    public static GuiScreen openMenu(EntityPlayer entityplayer) {
        boolean[] map_factions = LOTRMiniQuestWelcome.forceMenu_Map_Factions(entityplayer);
        if(map_factions[0]) {
            return new LOTRGuiMap();
        }
        if(map_factions[1]) {
            return new LOTRGuiFactions();
        }
        if(lastMenuScreen != null) {
            try {
                return lastMenuScreen.newInstance();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return new LOTRGuiMenu();
    }
}
