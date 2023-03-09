
package kom.feuerwehr.task;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class ReadExcel {
   private String inputFile;

   // Sammelplatz
   private String[] sammelplatzKoordinaten = new String[2];
   private String[] sammelplatz = new String[2];
   // Melbeck
   private String[] koordinaten182410 = new String[7];
   private String[] bemerkung182410 = new String[7];
   private String[] fragen182410 = new String[7];
   private String[] antworten182410 = new String[7];
   private String[] koordinaten184410 = new String[7];
   private String[] bemerkung184410 = new String[7];
   private String[] fragen184410 = new String[7];
   private String[] antworten184410 = new String[7];
   private String[] koordinaten184310 = new String[7];
   private String[] bemerkung184310 = new String[7];
   private String[] fragen184310 = new String[7];
   private String[] antworten184310 = new String[7];
   private String[] koordinaten181710 = new String[7];
   private String[] bemerkung181710 = new String[7];
   private String[] fragen181710 = new String[7];
   private String[] antworten181710 = new String[7];

   // Deutsch Evern
   private String[] koordinaten181720 = new String[7];
   private String[] bemerkung181720 = new String[7];
   private String[] fragen181720 = new String[7];
   private String[] antworten181720 = new String[7];
   private String[] koordinaten182420 = new String[7];
   private String[] bemerkung182420 = new String[7];
   private String[] fragen182420 = new String[7];
   private String[] antworten182420 = new String[7];
   private String[] koordinaten184520 = new String[7];
   private String[] bemerkung184520 = new String[7];
   private String[] fragen184520 = new String[7];
   private String[] antworten184520 = new String[7];

   // Embsen
   private String[] koordinaten181730 = new String[7];
   private String[] bemerkung181730 = new String[7];
   private String[] fragen181730 = new String[7];
   private String[] antworten181730 = new String[7];
   private String[] koordinaten185130 = new String[7];
   private String[] bemerkung185130 = new String[7];
   private String[] fragen185130 = new String[7];
   private String[] antworten185130 = new String[7];
   private String[] koordinaten184730 = new String[7];
   private String[] bemerkung184730 = new String[7];
   private String[] fragen184730 = new String[7];
   private String[] antworten184730 = new String[7];

   // Oerzen
   private String[] koordinaten181732 = new String[7];
   private String[] bemerkung181732 = new String[7];
   private String[] fragen181732 = new String[7];
   private String[] antworten181732 = new String[7];
   private String[] koordinaten184032 = new String[7];
   private String[] bemerkung184032 = new String[7];
   private String[] fragen184032 = new String[7];
   private String[] antworten184032 = new String[7];
   private String[] koordinaten182332 = new String[7];
   private String[] bemerkung182332 = new String[7];
   private String[] fragen182332 = new String[7];
   private String[] antworten182332 = new String[7];

   // Barnstedt
   private String[] koordinaten181740 = new String[7];
   private String[] bemerkung181740 = new String[7];
   private String[] fragen181740 = new String[7];
   private String[] antworten181740 = new String[7];
   private String[] koordinaten184040 = new String[7];
   private String[] bemerkung184040 = new String[7];
   private String[] fragen184040 = new String[7];
   private String[] antworten184040 = new String[7];
   private String[] koordinaten182340 = new String[7];
   private String[] bemerkung182340 = new String[7];
   private String[] fragen182340 = new String[7];
   private String[] antworten182340 = new String[7];

   // Kolkhagen
   private String[] koordinaten184342 = new String[7];
   private String[] bemerkung184342 = new String[7];
   private String[] fragen184342 = new String[7];
   private String[] antworten184342 = new String[7];

   // Gast
   private String[] koordinatenGast1 = new String[7];
   private String[] bemerkungGast1 = new String[7];
   private String[] fragenGast1 = new String[7];
   private String[] antwortenGast1 = new String[7];

   private String[] koordinatenGast2 = new String[7];
   private String[] bemerkungGast2 = new String[7];
   private String[] fragenGast2 = new String[7];
   private String[] antwortenGast2 = new String[7];

   public void setInputFile( String inputFile ) {
      this.inputFile = inputFile;
   }

   public void read( ) throws IOException {
      File inputWorkbook = new File( inputFile );
      Workbook w;
      try {
         w = Workbook.getWorkbook( inputWorkbook );
         // Get all sheets
         for( int index = 0; index < w.getNumberOfSheets(); index++ ) {
            Sheet sheet = w.getSheet( index );
            String[] shit = w.getSheetNames();

            if( shit[index].equals( "Sammelplatz" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        if( i < 2 )
                           sammelplatzKoordinaten[i] = cell.getContents();
                     } else if( j == 1 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 1, i );
                        if( i < 2 )
                           sammelplatz[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-24-10" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten182410[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung182410[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen182410[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten182410[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-44-10" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184410[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184410[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184410[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184410[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-43-10" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184310[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184310[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184310[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184310[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-17-10" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten181710[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung181710[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen181710[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten181710[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-45-20" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184520[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184520[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184520[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184520[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-24-20" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten182420[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung182420[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen182420[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten182420[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-17-20" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten181720[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung181720[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen181720[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten181720[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-17-30" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten181730[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung181730[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen181730[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten181730[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-51-30" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten185130[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung185130[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen185130[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten185130[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-47-30" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184730[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184730[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184730[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184730[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-17-32" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten181732[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung181732[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen181732[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten181732[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-40-32" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184032[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184032[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184032[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184032[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-23-32" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten182332[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung182332[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen182332[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten182332[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-17-40" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten181740[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung181740[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen181740[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten181740[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-40-40" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184040[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184040[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184040[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184040[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-23-40" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten182340[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung182340[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen182340[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten182340[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "18-43-42" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinaten184342[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkung184342[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragen184342[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antworten184342[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "Gast1" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinatenGast1[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkungGast1[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragenGast1[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antwortenGast1[i] = cell.getContents();
                     }
                  }
               }
            } else if( shit[index].equals( "Gast2" ) ) {
               for( int j = 0; j < sheet.getColumns(); j++ ) {
                  for( int i = 1; i < sheet.getRows(); i++ ) {
                     if( j == 0 ) {
                        // handle koordinaten
                        Cell cell = sheet.getCell( 0, i );
                        koordinatenGast2[i] = cell.getContents();
                     } else if( j == 1 ) {
                        Cell cell = sheet.getCell( 1, i );
                        bemerkungGast2[i] = cell.getContents();
                     } else if( j == 2 ) {
                        // handle fragen
                        Cell cell = sheet.getCell( 2, i );
                        fragenGast2[i] = cell.getContents();
                     } else if( j == 3 ) {
                        // handle antworten
                        Cell cell = sheet.getCell( 3, i );
                        antwortenGast2[i] = cell.getContents();
                     }
                  }
               }
            }
         }
      } catch( BiffException e ) {
         e.printStackTrace();
      }
   }

   public String[] getKoordinaten182410( ) {
      return koordinaten182410;
   }

   public String[] getFragen182410( ) {
      return fragen182410;
   }

   public String[] getAntworten182410( ) {
      return antworten182410;
   }

   public String[] getKoordinaten184410( ) {
      return koordinaten184410;
   }

   public String[] getFragen184410( ) {
      return fragen184410;
   }

   public String[] getAntworten184410( ) {
      return antworten184410;
   }

   public String[] getKoordinaten184310( ) {
      return koordinaten184310;
   }

   public String[] getFragen184310( ) {
      return fragen184310;
   }

   public String[] getAntworten184310( ) {
      return antworten184310;
   }

   public String[] getKoordinaten181710( ) {
      return koordinaten181710;
   }

   public String[] getFragen181710( ) {
      return fragen181710;
   }

   public String[] getAntworten181710( ) {
      return antworten181710;
   }

   public String[] getKoordinaten181720( ) {
      return koordinaten181720;
   }

   public String[] getFragen181720( ) {
      return fragen181720;
   }

   public String[] getAntworten181720( ) {
      return antworten181720;
   }

   public String[] getKoordinaten182420( ) {
      return koordinaten182420;
   }

   public String[] getFragen182420( ) {
      return fragen182420;
   }

   public String[] getAntworten182420( ) {
      return antworten182420;
   }

   public String[] getKoordinaten184520( ) {
      return koordinaten184520;
   }

   public String[] getFragen184520( ) {
      return fragen184520;
   }

   public String[] getAntworten184520( ) {
      return antworten184520;
   }

   public String[] getKoordinaten181730( ) {
      return koordinaten181730;
   }

   public String[] getFragen181730( ) {
      return fragen181730;
   }

   public String[] getAntworten181730( ) {
      return antworten181730;
   }

   public String[] getKoordinaten185130( ) {
      return koordinaten185130;
   }

   public String[] getFragen185130( ) {
      return fragen185130;
   }

   public String[] getAntworten185130( ) {
      return antworten185130;
   }

   public String[] getKoordinaten184730( ) {
      return koordinaten184730;
   }

   public String[] getFragen184730( ) {
      return fragen184730;
   }

   public String[] getAntworten184730( ) {
      return antworten184730;
   }

   public String[] getKoordinaten181732( ) {
      return koordinaten181732;
   }

   public String[] getFragen181732( ) {
      return fragen181732;
   }

   public String[] getAntworten181732( ) {
      return antworten181732;
   }

   public String[] getKoordinaten184032( ) {
      return koordinaten184032;
   }

   public String[] getFragen184032( ) {
      return fragen184032;
   }

   public String[] getAntworten184032( ) {
      return antworten184032;
   }

   public String[] getKoordinaten182332( ) {
      return koordinaten182332;
   }

   public String[] getFragen182332( ) {
      return fragen182332;
   }

   public String[] getAntworten182332( ) {
      return antworten182332;
   }

   public String[] getKoordinaten181740( ) {
      return koordinaten181740;
   }

   public String[] getFragen181740( ) {
      return fragen181740;
   }

   public String[] getAntworten181740( ) {
      return antworten181740;
   }

   public String[] getKoordinaten184040( ) {
      return koordinaten184040;
   }

   public String[] getFragen184040( ) {
      return fragen184040;
   }

   public String[] getAntworten184040( ) {
      return antworten184040;
   }

   public String[] getKoordinaten182340( ) {
      return koordinaten182340;
   }

   public String[] getFragen182340( ) {
      return fragen182340;
   }

   public String[] getAntworten182340( ) {
      return antworten182340;
   }

   public String[] getKoordinaten184342( ) {
      return koordinaten184342;
   }

   public String[] getFragen184342( ) {
      return fragen184342;
   }

   public String[] getAntworten184342( ) {
      return antworten184342;
   }

   public String[] getSammelplatzKoordinaten( ) {
      return sammelplatzKoordinaten;
   }

   public String[] getSammelplatz( ) {
      return sammelplatz;
   }

   public String[] getBemerkung182410( ) {
      return bemerkung182410;
   }

   public String[] getBemerkung184410( ) {
      return bemerkung184410;
   }

   public String[] getBemerkung184310( ) {
      return bemerkung184310;
   }

   public String[] getBemerkung181710( ) {
      return bemerkung181710;
   }

   public String[] getBemerkung181720( ) {
      return bemerkung181720;
   }

   public String[] getBemerkung182420( ) {
      return bemerkung182420;
   }

   public String[] getBemerkung184520( ) {
      return bemerkung184520;
   }

   public String[] getBemerkung181730( ) {
      return bemerkung181730;
   }

   public String[] getBemerkung185130( ) {
      return bemerkung185130;
   }

   public String[] getBemerkung184730( ) {
      return bemerkung184730;
   }

   public String[] getBemerkung181732( ) {
      return bemerkung181732;
   }

   public String[] getBemerkung184032( ) {
      return bemerkung184032;
   }

   public String[] getBemerkung182332( ) {
      return bemerkung182332;
   }

   public String[] getBemerkung181740( ) {
      return bemerkung181740;
   }

   public String[] getBemerkung184040( ) {
      return bemerkung184040;
   }

   public String[] getBemerkung182340( ) {
      return bemerkung182340;
   }

   public String[] getBemerkung184342( ) {
      return bemerkung184342;
   }

   public String[] getKoordinaten( String rufname ) {
      switch( rufname ) {
         // Barnstedt
         case "18-23-40":
            return koordinaten182340;
         case "18-40-40":
            return koordinaten184040;
         case "18-17-40":
            return koordinaten181740;
         // Deutsch Evern
         case "18-24-20":
            return koordinaten182420;
         case "18-45-20":
            return koordinaten184520;
         case "18-17-20":
            return koordinaten181720;
         // Embsen
         case "18-51-30":
            return koordinaten185130;
         case "18-47-30":
            return koordinaten184730;
         case "18-17-30":
            return koordinaten181730;
         // Kolkhagen
         case "18-43-42":
            return koordinaten184342;
         // Melbeck
         case "18-17-10":
            return koordinaten181710;
         case "18-43-10":
            return koordinaten184310;
         case "18-44-10":
            return koordinaten184410;
         case "18-24-10":
            return koordinaten182410;
         // Oerzen
         case "18-40-32":
            return koordinaten184032;
         case "18-23-32":
            return koordinaten182332;
         case "18-17-32":
            return koordinaten181732;
         case "Gast1":
            return koordinatenGast1;
         case "Gast2":
            return koordinatenGast2;
         default:
            System.out.println( "Something going wrong!" );
            break;

      }
      return null;
   }

   public String[] getBemerkung( String rufname ) {
      switch( rufname ) {
         // Barnstedt
         case "18-23-40":
            return bemerkung182340;
         case "18-40-40":
            return bemerkung184040;
         case "18-17-40":
            return bemerkung181740;
         // Deutsch Evern
         case "18-24-20":
            return bemerkung182420;
         case "18-45-20":
            return bemerkung184520;
         case "18-17-20":
            return bemerkung181720;
         // Embsen
         case "18-51-30":
            return bemerkung185130;
         case "18-47-30":
            return bemerkung184730;
         case "18-17-30":
            return bemerkung181730;
         // Kolkhagen
         case "18-43-42":
            return bemerkung184342;
         // Melbeck
         case "18-17-10":
            return bemerkung181710;
         case "18-43-10":
            return bemerkung184310;
         case "18-44-10":
            return bemerkung184410;
         case "18-24-10":
            return bemerkung182410;
         // Oerzen
         case "18-40-32":
            return bemerkung184032;
         case "18-23-32":
            return bemerkung182332;
         case "18-17-32":
            return bemerkung181732;
         case "Gast1":
            return bemerkungGast1;
         case "Gast2":
            return bemerkungGast2;
         default:
            System.out.println( "Something going wrong!" );
            break;

      }
      return null;
   }

   public String[] getFragen( String rufname ) {
      switch( rufname ) {
         // Barnstedt
         case "18-23-40":
            return fragen182340;
         case "18-40-40":
            return fragen184040;
         case "18-17-40":
            return fragen181740;
         // Deutsch Evern
         case "18-24-20":
            return fragen182420;
         case "18-45-20":
            return fragen184520;
         case "18-17-20":
            return fragen181720;
         // Embsen
         case "18-51-30":
            return fragen185130;
         case "18-47-30":
            return fragen184730;
         case "18-17-30":
            return fragen181730;
         // Kolkhagen
         case "18-43-42":
            return fragen184342;
         // Melbeck
         case "18-17-10":
            return fragen181710;
         case "18-43-10":
            return fragen184310;
         case "18-44-10":
            return fragen184410;
         case "18-24-10":
            return fragen182410;
         // Oerzen
         case "18-40-32":
            return fragen184032;
         case "18-23-32":
            return fragen182332;
         case "18-17-32":
            return fragen181732;
         case "Gast1":
            return fragenGast1;
         case "Gast2":
            return fragenGast2;
         default:
            System.out.println( "Something going wrong!" );
            break;

      }
      return null;
   }

   public String[] getAntworten( String rufname ) {
      switch( rufname ) {
         // Barnstedt
         case "18-23-40":
            return antworten182340;
         case "18-40-40":
            return antworten184040;
         case "18-17-40":
            return antworten181740;
         // Deutsch Evern
         case "18-24-20":
            return antworten182420;
         case "18-45-20":
            return antworten184520;
         case "18-17-20":
            return antworten181720;
         // Embsen
         case "18-51-30":
            return antworten185130;
         case "18-47-30":
            return antworten184730;
         case "18-17-30":
            return antworten181730;
         // Kolkhagen
         case "18-43-42":
            return antworten184342;
         // Melbeck
         case "18-17-10":
            return antworten181710;
         case "18-43-10":
            return antworten184310;
         case "18-44-10":
            return antworten184410;
         case "18-24-10":
            return antworten182410;
         // Oerzen
         case "18-40-32":
            return antworten184032;
         case "18-23-32":
            return antworten182332;
         case "18-17-32":
            return antworten181732;
         case "Gast1":
            return antwortenGast1;
         case "Gast2":
            return antwortenGast2;
         default:
            System.out.println( "Something going wrong!" );
            break;
      }
      return null;
   }

}
