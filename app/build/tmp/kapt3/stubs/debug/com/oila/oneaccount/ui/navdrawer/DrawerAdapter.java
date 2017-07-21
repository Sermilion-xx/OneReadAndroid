package com.oila.oneaccount.ui.navdrawer;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0014\u0015B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u001d\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\bH\u0016\u00a2\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0016\u00a2\u0006\u0002\u0010\u0013R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter$DrawerViewHolder;", "mItems", "", "Lnet/oneread/oneread/data/model/DrawerItem;", "(Ljava/util/List;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "(Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter/DrawerViewHolder;I)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "(Landroid/view/ViewGroup;I)Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter/DrawerViewHolder;", "Companion", "DrawerViewHolder", "app_debug"})
public final class DrawerAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.oila.oneaccount.ui.navdrawer.DrawerAdapter.DrawerViewHolder> {
    private final java.util.List<net.oneread.oneread.data.model.DrawerItem> mItems = null;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_ITEM_SEPARATOR = 2;
    public static final com.oila.oneaccount.ui.navdrawer.DrawerAdapter.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.oila.oneaccount.ui.navdrawer.DrawerAdapter.DrawerViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.oila.oneaccount.ui.navdrawer.DrawerAdapter.DrawerViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public DrawerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<net.oneread.oneread.data.model.DrawerItem> mItems) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter$DrawerViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "viewType", "", "(Landroid/view/View;I)V", "mImageView", "Landroid/widget/ImageView;", "getMImageView", "()Landroid/widget/ImageView;", "setMImageView", "(Landroid/widget/ImageView;)V", "mProfileImage", "Lde/hdodenhof/circleimageview/CircleImageView;", "getMProfileImage", "()Lde/hdodenhof/circleimageview/CircleImageView;", "setMProfileImage", "(Lde/hdodenhof/circleimageview/CircleImageView;)V", "mTitle", "Landroid/widget/TextView;", "getMTitle", "()Landroid/widget/TextView;", "app_debug"})
    public static final class DrawerViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        public de.hdodenhof.circleimageview.CircleImageView mProfileImage;
        @org.jetbrains.annotations.NotNull()
        public android.widget.ImageView mImageView;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView mTitle = null;
        
        @org.jetbrains.annotations.NotNull()
        public final de.hdodenhof.circleimageview.CircleImageView getMProfileImage() {
            return null;
        }
        
        public final void setMProfileImage(@org.jetbrains.annotations.NotNull()
        de.hdodenhof.circleimageview.CircleImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getMImageView() {
            return null;
        }
        
        public final void setMImageView(@org.jetbrains.annotations.NotNull()
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getMTitle() {
            return null;
        }
        
        public DrawerViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view, int viewType) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0082D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0082D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/oila/oneaccount/ui/navdrawer/DrawerAdapter$Companion;", "", "()V", "TYPE_HEADER", "", "getTYPE_HEADER", "()I", "TYPE_ITEM", "getTYPE_ITEM", "TYPE_ITEM_SEPARATOR", "getTYPE_ITEM_SEPARATOR", "app_debug"})
    public static final class Companion {
        
        private final int getTYPE_HEADER() {
            return 0;
        }
        
        private final int getTYPE_ITEM() {
            return 0;
        }
        
        private final int getTYPE_ITEM_SEPARATOR() {
            return 0;
        }
        
        private Companion() {
            super();
        }
    }
}