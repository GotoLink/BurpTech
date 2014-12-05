package burptech.item.crafting;

public final class RecipeManager
{
    public static final RecipeManager INSTANCE = new RecipeManager();
    private RecipeManager(){}

    public void addRecipes(IAdder...adders)
    {
        for(IAdder adder:adders){
            adder.addRecipes();
        }
    }

    public void postInitialization(IPostMaker...postInits)
    {
        for(IPostMaker maker:postInits){
            maker.postInitialization();
        }
    }

    public static interface IAdder{
        public void addRecipes();
    }

    public static interface IPostMaker{
        public void postInitialization();
    }
}
