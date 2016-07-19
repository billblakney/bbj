package bbj.fx.treetable;

import java.math.BigDecimal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableEntry
{
   private SimpleIntegerProperty id;

   private SimpleStringProperty someString;

   private SimpleLongProperty someLong;

   private SimpleDoubleProperty someDouble;

   private SimpleObjectProperty<BigDecimal> someBigDecimal;
   
   private SimpleBooleanProperty someBoolean;

   public TableEntry()
   {
   }

//TODO IP
   public TableEntry(Integer id, String someString, Long someLong,
         Double someDouble, BigDecimal someBigDecimal,
         boolean someBoolean,int x)
   {
     this.id = new SimpleIntegerProperty(id);
     this.someString = new SimpleStringProperty(someString);
     this.someLong = new SimpleLongProperty(someLong);
     this.someDouble = new SimpleDoubleProperty(someDouble);
     this.someBigDecimal = new SimpleObjectProperty(someBigDecimal);
     this.someBoolean = new SimpleBooleanProperty(someBoolean);
   }

   public TableEntry(Integer id, String someString, long someLong,
         double someDouble, BigDecimal someBigDecimal, boolean someBoolean)
   {
     this.id = new SimpleIntegerProperty(id);
     this.someString = new SimpleStringProperty(someString);
     this.someLong = new SimpleLongProperty(someLong);
     this.someDouble = new SimpleDoubleProperty(someDouble);
     this.someBigDecimal = new SimpleObjectProperty(someBigDecimal);
     this.someBoolean = new SimpleBooleanProperty(someBoolean);
   }
   
   @Override
   public String toString()
   {
      StringBuilder tBuilder = new StringBuilder();
      tBuilder.append("id=" + getId() + ",");
      tBuilder.append("someString=" + getSomeString() + ",");
      tBuilder.append("someLong=" + getSomeLong() + ",");
      tBuilder.append("someDouble=" + getSomeDouble() + ",");
      tBuilder.append("someBigDecimal=" + getSomeBigDecimal() + ",");
      tBuilder.append("someBoolean=" + getSomeBoolean());
      return tBuilder.toString();
   }

   public SimpleIntegerProperty idProperty() {
     if (id == null) {
       id = new SimpleIntegerProperty(this, "id");
     }
     return id;
   }

   public SimpleStringProperty someStringProperty() {
     if (someString == null) {
       someString = new SimpleStringProperty(this, "someString");
     }
     return someString;
   }

   public SimpleDoubleProperty someDoubleProperty() {
     if (someDouble == null) {
       someDouble = new SimpleDoubleProperty(this, "someDouble");
     }
     return someDouble;
   }

   public SimpleLongProperty someLongProperty() {
     if (someLong == null) {
       someLong = new SimpleLongProperty(this, "someLong");
     }
     return someLong;
   }

   public SimpleObjectProperty<BigDecimal> someBigDecimalProperty() {
     if (someBigDecimal == null) {
       someBigDecimal = new SimpleObjectProperty<BigDecimal>(this, "0.0");
     }
     return someBigDecimal;
   }

   public SimpleBooleanProperty someBooleanProperty() {
     if (someBoolean == null) {
       someBoolean = new SimpleBooleanProperty(this, "someBoolean");
     }
     return someBoolean;
   }

   public Integer getId() {
     return id.get();
   }

   public void setId(Integer fName) {
     id.set(fName);
   }

   public String getSomeString() {
     return someString.get();
   }

   public void setSomeString(String fName) {
     someString.set(fName);
   }

   public long getSomeLong() {
     return someLong.get();
   }

   public void setSomeLong(long fName) {
     someLong.set(fName);
   }

   public double getSomeDouble() {
     return someDouble.get();
   }

   public void setSomeDouble(double fName) {
     someDouble.set(fName);
   }

   public BigDecimal getSomeBigDecimal() {
     return someBigDecimal.getValue();
   }

   public void setSomeBigDecimal(BigDecimal fName) {
     someBigDecimal.set(fName);
   }

   public Boolean getSomeBoolean() {
     return someBoolean.getValue();
   }

   public void setSomeBoolean(Boolean fName) {
     someBoolean.set(fName);
   }
}
