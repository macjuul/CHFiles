/*  1:   */ package me.macjuul.chfiles;
/*  2:   */ 
/*  3:   */ import com.laytonsmith.PureUtilities.SimpleVersion;
/*  4:   */ import com.laytonsmith.PureUtilities.Version;
/*  5:   */ import com.laytonsmith.core.extensions.AbstractExtension;
/*  6:   */ import com.laytonsmith.core.extensions.MSExtension;
/*  7:   */ import java.io.PrintStream;
/*  8:   */ 
/*  9:   */ @MSExtension("CHFiles")
/* 10:   */ public class LifeCycle
/* 11:   */   extends AbstractExtension
/* 12:   */ {
/* 13:   */   public Version getVersion()
/* 14:   */   {
/* 15:14 */     return new SimpleVersion(1, 0, 3);
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void onStartup()
/* 19:   */   {
/* 20:19 */     System.out.println("CHFiles " + getVersion() + " has sucessfully been enabled!");
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void onShutdown()
/* 24:   */   {
/* 25:24 */     System.out.println("CHFiles " + getVersion() + " has sucessfully been disabled!");
/* 26:   */   }
/* 27:   */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.LifeCycle
 * JD-Core Version:    0.7.0.1
 */