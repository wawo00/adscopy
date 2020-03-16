package main.java;

/**
 * 用于supprot类库替换为androidx
 *
 */
public class AndroidxMapping {
    public static String Support_Prex="android.support";
    public static String sup_NonNull="android.support.annotation.NonNull";
    public static String ax_NonNull="androidx.annotation.NonNull";

    public static String sup_LinearLayoutManager="android.support.v7.widget.LinearLayoutManager";
    public static String sup_OrientationHelper="android.support.v7.widget.OrientationHelper";
    public static String sup_RecyclerView="android.support.v7.widget.RecyclerView";
    public static String ax_LinearLayoutManager="androidx.recyclerview.widget.LinearLayoutManager";
    public static String ax_OrientationHelper="androidx.recyclerview.widget.OrientationHelper";
    public static String ax_RecyclerView="androidx.recyclerview.widget.RecyclerView";

    public static String sup_Fragment="android.support.v4.app.Fragment";
    public static String ax_Fragment="androidx.fragment.app.Fragment";

    public static String sup_vertical="OrientationHelper.VERTICAL";
    public static String ax_vertical="RecyclerView.VERTICAL";

    public static String sup_FragmentActivity="android.support.v4.app.FragmentActivity";
    public static String sup_FragmentManager="android.support.v4.app.FragmentManager";
    public static String sup_FragmentTransactionl="android.support.v4.app.FragmentTransaction";
    public static String ax_FragmentActivity="androidx.fragment.app.FragmentActivity";
    public static String ax_FragmentManager="androidx.fragment.app.FragmentManager";
    public static String ax_FragmentTransactionl="androidx.fragment.app.FragmentTransaction";

    public static String sup_ContextCompat="android.support.v4.content.ContextCompat";
    public static String ax_ContextCompat="androidx.core.content.ContextCompat";

}
