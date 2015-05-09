/*   1:    */ package me.macjuul.chfiles.functions;
/*   2:    */ 
/*   3:    */ import com.laytonsmith.PureUtilities.Version;
/*   4:    */ import com.laytonsmith.annotations.api;
/*   5:    */ import com.laytonsmith.core.CHVersion;
/*   6:    */ import com.laytonsmith.core.constructs.CArray;
/*   7:    */ import com.laytonsmith.core.constructs.CString;
/*   8:    */ import com.laytonsmith.core.constructs.Construct;
/*   9:    */ import com.laytonsmith.core.constructs.Target;
/*  10:    */ import com.laytonsmith.core.environments.Environment;
/*  11:    */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/*  12:    */ import com.laytonsmith.core.functions.AbstractFunction;
/*  13:    */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/*  14:    */ import java.io.File;
/*  15:    */ 
/*  16:    */ @api
/*  17:    */ public class functions$list_files
/*  18:    */   extends AbstractFunction
/*  19:    */ {
/*  20:    */   public Exceptions.ExceptionType[] thrown()
/*  21:    */   {
/*  22:439 */     return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException };
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean isRestricted()
/*  26:    */   {
/*  27:444 */     return true;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public Boolean runAsync()
/*  31:    */   {
/*  32:449 */     return Boolean.valueOf(true);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public Construct exec(Target t, Environment environment, Construct... args)
/*  36:    */     throws ConfigRuntimeException
/*  37:    */   {
/*  38:455 */     CArray ret = new CArray(t);
/*  39:456 */     String location = args[0].val();
/*  40:457 */     location = new File(t.file().getParentFile(), location).getAbsolutePath();
/*  41:458 */     File path = new File(location);
/*  42:459 */     if (!path.isDirectory()) {
/*  43:460 */       throw new ConfigRuntimeException("Path is not a directory.", Exceptions.ExceptionType.IOException, t);
/*  44:    */     }
/*  45:462 */     String[] list = path.list();
/*  46:463 */     for (String s : list) {
/*  47:464 */       ret.push(new CString(s, t));
/*  48:    */     }
/*  49:466 */     return ret;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public String getName()
/*  53:    */   {
/*  54:471 */     return "list_files";
/*  55:    */   }
/*  56:    */   
/*  57:    */   public Integer[] numArgs()
/*  58:    */   {
/*  59:476 */     return new Integer[] { Integer.valueOf(1) };
/*  60:    */   }
/*  61:    */   
/*  62:    */   public String docs()
/*  63:    */   {
/*  64:481 */     return " ";
/*  65:    */   }
/*  66:    */   
/*  67:    */   public Version since()
/*  68:    */   {
/*  69:486 */     return CHVersion.V3_3_1;
/*  70:    */   }
/*  71:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions.list_files
 * JD-Core Version:    0.7.0.1
 */