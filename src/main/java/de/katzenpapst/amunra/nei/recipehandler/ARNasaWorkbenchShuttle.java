package de.katzenpapst.amunra.nei.recipehandler;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import de.katzenpapst.amunra.client.gui.schematic.GuiSchematicShuttle;
import de.katzenpapst.amunra.nei.NEIAmunRaConfig;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.planets.mars.nei.NEIGalacticraftMarsConfig;
import micdoodle8.mods.galacticraft.planets.mars.nei.RocketT2RecipeHandler;
import micdoodle8.mods.galacticraft.planets.mars.nei.RocketT2RecipeHandler.CachedRocketRecipe;
import net.minecraft.item.ItemStack;

public class ARNasaWorkbenchShuttle extends TemplateRecipeHandler {

    public class CachedRocketRecipe extends TemplateRecipeHandler.CachedRecipe
    {
        public ArrayList<PositionedStack> input;
        public PositionedStack output;

        @Override
        public ArrayList<PositionedStack> getIngredients()
        {
            return this.input;
        }

        @Override
        public PositionedStack getResult()
        {
            return this.output;
        }

        public CachedRocketRecipe(ArrayList<PositionedStack> pstack1, PositionedStack pstack2)
        {
            super();
            this.input = pstack1;
            this.output = pstack2;
        }

        public CachedRocketRecipe(Map.Entry<ArrayList<PositionedStack>, PositionedStack> recipe)
        {
            this(recipe.getKey(), recipe.getValue());
        }
    }

    public ARNasaWorkbenchShuttle() {
    }

    public String getRecipeId()
    {
        return "amunra.rocketShuttle";
    }


    @Override
    public int recipiesPerPage()
    {
        return 1;
    }

    @Override
    public String getRecipeName() {
        return GCCoreUtil.translate("tile.rocketWorkbench.name");
    }

    @Override
    public String getGuiTexture() {
        return GuiSchematicShuttle.shuttleSchematicTexture.toString();
    }

    public Set<Entry<ArrayList<PositionedStack>, PositionedStack>> getRecipes()
    {
        return NEIAmunRaConfig.getShuttleRecipes();
    }

    @Override
    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GuiDraw.changeTexture(getGuiTexture());

        GuiDraw.drawTexturedModalRect(0, 4, 4, 4, 168, 130);
    }

    @Override
    public void loadTransferRects()
    {
        //?
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals(this.getRecipeId()))
        {
            for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
            {
                this.arecipes.add(new CachedRocketRecipe(irecipe));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getValue().item, result))
            {
                this.arecipes.add(new CachedRocketRecipe(irecipe));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        for (final Map.Entry<ArrayList<PositionedStack>, PositionedStack> irecipe : this.getRecipes())
        {
            for (final PositionedStack pstack : irecipe.getKey())
            {
                if (NEIServerUtils.areStacksSameTypeCrafting(ingredient, pstack.item))
                {
                    this.arecipes.add(new CachedRocketRecipe(irecipe));
                    break;
                }
            }
        }
    }



    @Override
    public void drawForeground(int recipe)
    {
    }

}
