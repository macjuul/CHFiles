/*   1:    */ package me.macjuul.chfiles.functions;
/*   2:    */ 
/*   3:    */ import com.laytonsmith.PureUtilities.Common.FileUtil;
/*   4:    */ import com.laytonsmith.PureUtilities.Version;
/*   5:    */ import com.laytonsmith.annotations.api;
/*   6:    */ import com.laytonsmith.core.CHVersion;
/*   7:    */ import com.laytonsmith.core.Security;
/*   8:    */ import com.laytonsmith.core.Static;
/*   9:    */ import com.laytonsmith.core.constructs.CArray;
/*  10:    */ import com.laytonsmith.core.constructs.CBoolean;
/*  11:    */ import com.laytonsmith.core.constructs.CString;
/*  12:    */ import com.laytonsmith.core.constructs.CVoid;
/*  13:    */ import com.laytonsmith.core.constructs.Construct;
/*  14:    */ import com.laytonsmith.core.constructs.Target;
/*  15:    */ import com.laytonsmith.core.environments.Environment;
/*  16:    */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/*  17:    */ import com.laytonsmith.core.functions.AbstractFunction;
/*  18:    */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/*  19:    */ import java.io.File;
/*  20:    */ import java.util.logging.Level;
/*  21:    */ import java.util.logging.Logger;
/*  22:    */ import org.apache.commons.io.FileUtils;
/*  23:    */ 
/*  24:    */ public class functions
/*  25:    */ {
/*  26:    */   public static String docs()
/*  27:    */   {
/*  28: 29 */     return "This Extension will add some sick things to CommandHelper!";
/*  29:    */   }
/*  30:    */   
/*  31:    */   @api
/*  32:    */   public static class create_file
/*  33:    */     extends AbstractFunction
/*  34:    */   {
/*  35:    */     public Exceptions.ExceptionType[] thrown()
/*  36:    */     {
/*  37: 38 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/*  38:    */     }
/*  39:    */     
/*  40:    */     public boolean isRestricted()
/*  41:    */     {
/*  42: 43 */       return true;
/*  43:    */     }
/*  44:    */     
/*  45:    */     public Boolean runAsync()
/*  46:    */     {
/*  47: 48 */       return Boolean.valueOf(true);
/*  48:    */     }
/*  49:    */     
/*  50:    */     public Construct exec(Target t, Environment environment, Construct... args)
/*  51:    */       throws ConfigRuntimeException
/*  52:    */     {
/*  53: 54 */       String location = args[0].val();
/*  54: 55 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/*  55: 56 */       if (!Security.CheckSecurity(location)) {
/*  56: 57 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/*  57:    */       }
/*  58:    */       try
/*  59:    */       {
/*  60: 61 */         File file = new File(location);
/*  61: 62 */         if (file.exists()) {
/*  62: 63 */           throw new ConfigRuntimeException(file.getAbsolutePath() + "Already Exists", Exceptions.ExceptionType.IOException, t);
/*  63:    */         }
/*  64: 65 */         FileUtil.write("", file, true);
/*  65: 66 */         return CVoid.VOID;
/*  66:    */       }
/*  67:    */       catch (Exception ex)
/*  68:    */       {
/*  69: 70 */         Static.getLogger().log(Level.SEVERE, "Could not be created while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/*  70:    */         
/*  71: 72 */         throw new ConfigRuntimeException("File could not be created.", Exceptions.ExceptionType.IOException, t);
/*  72:    */       }
/*  73:    */     }
/*  74:    */     
/*  75:    */     public String getName()
/*  76:    */     {
/*  77: 78 */       return "create_file";
/*  78:    */     }
/*  79:    */     
/*  80:    */     public Integer[] numArgs()
/*  81:    */     {
/*  82: 83 */       return new Integer[] { Integer.valueOf(1) };
/*  83:    */     }
/*  84:    */     
/*  85:    */     public String docs()
/*  86:    */     {
/*  87: 88 */       return " ";
/*  88:    */     }
/*  89:    */     
/*  90:    */     public Version since()
/*  91:    */     {
/*  92: 93 */       return CHVersion.V3_3_1;
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   @api
/*  97:    */   public static class delete_file
/*  98:    */     extends AbstractFunction
/*  99:    */   {
/* 100:    */     public Exceptions.ExceptionType[] thrown()
/* 101:    */     {
/* 102:103 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 103:    */     }
/* 104:    */     
/* 105:    */     public boolean isRestricted()
/* 106:    */     {
/* 107:108 */       return true;
/* 108:    */     }
/* 109:    */     
/* 110:    */     public Boolean runAsync()
/* 111:    */     {
/* 112:113 */       return Boolean.valueOf(true);
/* 113:    */     }
/* 114:    */     
/* 115:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 116:    */       throws ConfigRuntimeException
/* 117:    */     {
/* 118:119 */       String location = args[0].val();
/* 119:120 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 120:121 */       if (!Security.CheckSecurity(location)) {
/* 121:122 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 122:    */       }
/* 123:    */       try
/* 124:    */       {
/* 125:126 */         File file = new File(location);
/* 126:127 */         if (!file.exists()) {
/* 127:128 */           throw new ConfigRuntimeException(file.getAbsolutePath() + "Doesnt Exist", Exceptions.ExceptionType.IOException, t);
/* 128:    */         }
/* 129:130 */         if (file.isDirectory()) {
/* 130:131 */           FileUtils.deleteDirectory(file);
/* 131:    */         } else {
/* 132:133 */           FileUtils.forceDelete(file);
/* 133:    */         }
/* 134:135 */         return CVoid.VOID;
/* 135:    */       }
/* 136:    */       catch (Exception ex)
/* 137:    */       {
/* 138:139 */         Static.getLogger().log(Level.SEVERE, "Could not delete the file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 139:    */         
/* 140:141 */         throw new ConfigRuntimeException("File could not be deleted.", Exceptions.ExceptionType.IOException, t);
/* 141:    */       }
/* 142:    */     }
/* 143:    */     
/* 144:    */     public String getName()
/* 145:    */     {
/* 146:147 */       return "delete_file";
/* 147:    */     }
/* 148:    */     
/* 149:    */     public Integer[] numArgs()
/* 150:    */     {
/* 151:152 */       return new Integer[] { Integer.valueOf(1) };
/* 152:    */     }
/* 153:    */     
/* 154:    */     public String docs()
/* 155:    */     {
/* 156:157 */       return " ";
/* 157:    */     }
/* 158:    */     
/* 159:    */     public Version since()
/* 160:    */     {
/* 161:162 */       return CHVersion.V3_3_1;
/* 162:    */     }
/* 163:    */   }
/* 164:    */   
/* 165:    */   @api
/* 166:    */   public static class write_file
/* 167:    */     extends AbstractFunction
/* 168:    */   {
/* 169:    */     public Exceptions.ExceptionType[] thrown()
/* 170:    */     {
/* 171:172 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 172:    */     }
/* 173:    */     
/* 174:    */     public boolean isRestricted()
/* 175:    */     {
/* 176:177 */       return true;
/* 177:    */     }
/* 178:    */     
/* 179:    */     public Boolean runAsync()
/* 180:    */     {
/* 181:182 */       return Boolean.valueOf(true);
/* 182:    */     }
/* 183:    */     
/* 184:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 185:    */       throws ConfigRuntimeException
/* 186:    */     {
/* 187:188 */       String location = args[0].val();
/* 188:189 */       String content = args[1].val();
/* 189:190 */       int mode = 0;
/* 190:191 */       if (args.length == 3)
/* 191:    */       {
/* 192:193 */         String type = args[2].val().toUpperCase();
/* 193:194 */         if ((type.equals("OVERWRITE")) || (type.equals("APPEND")))
/* 194:    */         {
/* 195:196 */           if (type.equals("APPEND")) {
/* 196:197 */             mode = 1;
/* 197:    */           }
/* 198:    */         }
/* 199:    */         else {
/* 200:201 */           throw new ConfigRuntimeException("Argument 3 of write_file is invalid:" + args[3].val(), Exceptions.ExceptionType.FormatException, t);
/* 201:    */         }
/* 202:    */       }
/* 203:204 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 204:205 */       if (!Security.CheckSecurity(location)) {
/* 205:206 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 206:    */       }
/* 207:    */       try
/* 208:    */       {
/* 209:210 */         File file = new File(location);
/* 210:211 */         FileUtil.write(content, file, mode, false);
/* 211:212 */         return CVoid.VOID;
/* 212:    */       }
/* 213:    */       catch (Exception ex)
/* 214:    */       {
/* 215:216 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 216:    */         
/* 217:218 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 218:    */       }
/* 219:    */     }
/* 220:    */     
/* 221:    */     public String getName()
/* 222:    */     {
/* 223:224 */       return "write_file";
/* 224:    */     }
/* 225:    */     
/* 226:    */     public Integer[] numArgs()
/* 227:    */     {
/* 228:229 */       return new Integer[] { Integer.valueOf(2), Integer.valueOf(3) };
/* 229:    */     }
/* 230:    */     
/* 231:    */     public String docs()
/* 232:    */     {
/* 233:234 */       return " ";
/* 234:    */     }
/* 235:    */     
/* 236:    */     public Version since()
/* 237:    */     {
/* 238:239 */       return CHVersion.V3_3_1;
/* 239:    */     }
/* 240:    */   }
/* 241:    */   
/* 242:    */   @api
/* 243:    */   public static class copy_file
/* 244:    */     extends AbstractFunction
/* 245:    */   {
/* 246:    */     public Exceptions.ExceptionType[] thrown()
/* 247:    */     {
/* 248:249 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 249:    */     }
/* 250:    */     
/* 251:    */     public boolean isRestricted()
/* 252:    */     {
/* 253:254 */       return true;
/* 254:    */     }
/* 255:    */     
/* 256:    */     public Boolean runAsync()
/* 257:    */     {
/* 258:259 */       return Boolean.valueOf(true);
/* 259:    */     }
/* 260:    */     
/* 261:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 262:    */       throws ConfigRuntimeException
/* 263:    */     {
/* 264:265 */       String fromLocation = args[0].val();
/* 265:266 */       String toLocation = args[1].val();
/* 266:267 */       fromLocation = new File(t.file().getParentFile(), fromLocation).getAbsolutePath();
/* 267:268 */       toLocation = new File(t.file().getParentFile(), toLocation).getAbsolutePath();
/* 268:269 */       if ((!Security.CheckSecurity(toLocation)) || (!Security.CheckSecurity(fromLocation))) {
/* 269:270 */         throw new ConfigRuntimeException("You do not have access to some of the files", Exceptions.ExceptionType.SecurityException, t);
/* 270:    */       }
/* 271:272 */       File fromFile = new File(fromLocation);
/* 272:273 */       File toFile = new File(toLocation);
/* 273:    */       try
/* 274:    */       {
/* 275:276 */         if (fromFile.isDirectory()) {
/* 276:277 */           FileUtils.copyDirectory(fromFile, toFile);
/* 277:    */         } else {
/* 278:279 */           FileUtils.copyFile(fromFile, toFile);
/* 279:    */         }
/* 280:281 */         return CVoid.VOID;
/* 281:    */       }
/* 282:    */       catch (Exception ex)
/* 283:    */       {
/* 284:285 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(fromLocation).getAbsolutePath() + "\nFile " + (new File(fromLocation).exists() ? "exists" : "does not exist"));
/* 285:    */         
/* 286:287 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 287:    */       }
/* 288:    */     }
/* 289:    */     
/* 290:    */     public String getName()
/* 291:    */     {
/* 292:293 */       return "copy_file";
/* 293:    */     }
/* 294:    */     
/* 295:    */     public Integer[] numArgs()
/* 296:    */     {
/* 297:298 */       return new Integer[] { Integer.valueOf(2) };
/* 298:    */     }
/* 299:    */     
/* 300:    */     public String docs()
/* 301:    */     {
/* 302:303 */       return " ";
/* 303:    */     }
/* 304:    */     
/* 305:    */     public Version since()
/* 306:    */     {
/* 307:308 */       return CHVersion.V3_3_1;
/* 308:    */     }
/* 309:    */   }
/* 310:    */   
/* 311:    */   @api
/* 312:    */   public static class rename_file
/* 313:    */     extends AbstractFunction
/* 314:    */   {
/* 315:    */     public Exceptions.ExceptionType[] thrown()
/* 316:    */     {
/* 317:318 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 318:    */     }
/* 319:    */     
/* 320:    */     public boolean isRestricted()
/* 321:    */     {
/* 322:323 */       return true;
/* 323:    */     }
/* 324:    */     
/* 325:    */     public Boolean runAsync()
/* 326:    */     {
/* 327:328 */       return Boolean.valueOf(true);
/* 328:    */     }
/* 329:    */     
/* 330:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 331:    */       throws ConfigRuntimeException
/* 332:    */     {
/* 333:334 */       String location = args[0].val();
/* 334:335 */       String name = args[1].val();
/* 335:336 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 336:337 */       if (!Security.CheckSecurity(location)) {
/* 337:338 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 338:    */       }
/* 339:    */       try
/* 340:    */       {
/* 341:342 */         File file = new File(location);
/* 342:    */         
/* 343:344 */         File dir = file.getParentFile();
/* 344:345 */         File rename = new File(dir, name);
/* 345:347 */         if (rename.exists()) {
/* 346:347 */           throw new ConfigRuntimeException("File already exists!", Exceptions.ExceptionType.IOException, t);
/* 347:    */         }
/* 348:349 */         Boolean success = Boolean.valueOf(file.renameTo(rename));
/* 349:350 */         if (!success.booleanValue()) {
/* 350:351 */           throw new ConfigRuntimeException("Unable to rename file!", Exceptions.ExceptionType.IOException, t);
/* 351:    */         }
/* 352:353 */         return CVoid.VOID;
/* 353:    */       }
/* 354:    */       catch (Exception ex)
/* 355:    */       {
/* 356:357 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 357:    */         
/* 358:359 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 359:    */       }
/* 360:    */     }
/* 361:    */     
/* 362:    */     public String getName()
/* 363:    */     {
/* 364:365 */       return "rename_file";
/* 365:    */     }
/* 366:    */     
/* 367:    */     public Integer[] numArgs()
/* 368:    */     {
/* 369:370 */       return new Integer[] { Integer.valueOf(2) };
/* 370:    */     }
/* 371:    */     
/* 372:    */     public String docs()
/* 373:    */     {
/* 374:375 */       return " ";
/* 375:    */     }
/* 376:    */     
/* 377:    */     public Version since()
/* 378:    */     {
/* 379:380 */       return CHVersion.V3_3_1;
/* 380:    */     }
/* 381:    */   }
/* 382:    */   
/* 383:    */   @api
/* 384:    */   public static class file_exists
/* 385:    */     extends AbstractFunction
/* 386:    */   {
/* 387:    */     public Exceptions.ExceptionType[] thrown()
/* 388:    */     {
/* 389:390 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException };
/* 390:    */     }
/* 391:    */     
/* 392:    */     public boolean isRestricted()
/* 393:    */     {
/* 394:395 */       return true;
/* 395:    */     }
/* 396:    */     
/* 397:    */     public Boolean runAsync()
/* 398:    */     {
/* 399:400 */       return Boolean.valueOf(true);
/* 400:    */     }
/* 401:    */     
/* 402:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 403:    */       throws ConfigRuntimeException
/* 404:    */     {
/* 405:406 */       String location = new File(t.file().getParentFile(), args[0].val()).getAbsolutePath();
/* 406:407 */       File path = new File(location);
/* 407:    */       
/* 408:409 */       return CBoolean.get(path.exists());
/* 409:    */     }
/* 410:    */     
/* 411:    */     public String getName()
/* 412:    */     {
/* 413:414 */       return "file_exists";
/* 414:    */     }
/* 415:    */     
/* 416:    */     public Integer[] numArgs()
/* 417:    */     {
/* 418:419 */       return new Integer[] { Integer.valueOf(1) };
/* 419:    */     }
/* 420:    */     
/* 421:    */     public String docs()
/* 422:    */     {
/* 423:424 */       return " ";
/* 424:    */     }
/* 425:    */     
/* 426:    */     public Version since()
/* 427:    */     {
/* 428:429 */       return CHVersion.V3_3_1;
/* 429:    */     }
/* 430:    */   }
/* 431:    */   
/* 432:    */   @api
/* 433:    */   public static class list_files
/* 434:    */     extends AbstractFunction
/* 435:    */   {
/* 436:    */     public Exceptions.ExceptionType[] thrown()
/* 437:    */     {
/* 438:439 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException };
/* 439:    */     }
/* 440:    */     
/* 441:    */     public boolean isRestricted()
/* 442:    */     {
/* 443:444 */       return true;
/* 444:    */     }
/* 445:    */     
/* 446:    */     public Boolean runAsync()
/* 447:    */     {
/* 448:449 */       return Boolean.valueOf(true);
/* 449:    */     }
/* 450:    */     
/* 451:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 452:    */       throws ConfigRuntimeException
/* 453:    */     {
/* 454:455 */       CArray ret = new CArray(t);
/* 455:    */       
/* 456:    */ 
/* 457:458 */       String location = new File(t.file().getParentFile(), args[0].val()).getAbsolutePath();
/* 458:459 */       File path = new File(location);
/* 459:461 */       if (!path.isDirectory()) {
/* 460:462 */         throw new ConfigRuntimeException("Path is not a directory.", Exceptions.ExceptionType.IOException, t);
/* 461:    */       }
/* 462:464 */       String[] list = path.list();
/* 463:465 */       for (String s : list) {
/* 464:466 */         ret.push(new CString(s, t));
/* 465:    */       }
/* 466:468 */       return ret;
/* 467:    */     }
/* 468:    */     
/* 469:    */     public String getName()
/* 470:    */     {
/* 471:473 */       return "list_files";
/* 472:    */     }
/* 473:    */     
/* 474:    */     public Integer[] numArgs()
/* 475:    */     {
/* 476:478 */       return new Integer[] { Integer.valueOf(1) };
/* 477:    */     }
/* 478:    */     
/* 479:    */     public String docs()
/* 480:    */     {
/* 481:483 */       return " ";
/* 482:    */     }
/* 483:    */     
/* 484:    */     public Version since()
/* 485:    */     {
/* 486:488 */       return CHVersion.V3_3_1;
/* 487:    */     }
/* 488:    */   }
/* 489:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.2-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions
 * JD-Core Version:    0.7.0.1
 */