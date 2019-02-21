package com.exceeddata.ac.common.data.typedata;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import com.exceeddata.ac.common.data.typedata.mutable.MutableListData;
import com.exceeddata.ac.common.data.typedata.mutable.MutableMapData;
import com.exceeddata.ac.common.exception.EngineException;

public class TypeDataSerialization {
    private static final String location = System.getProperty("java.io.tmpdir") + "/typedata.ser";
    
    private String serializeExternalizable(final String loc, final TypeData data) throws IOException {
        final FileOutputStream fileOut = new FileOutputStream(loc);
        final ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
        outStream.writeObject(data);
        outStream.close();
        fileOut.close();
        return loc;
    }
    
    private TypeData deserializeExternalizable(final String loc) throws IOException, ClassNotFoundException {
        final FileInputStream fileIn = new FileInputStream(loc);
        final ObjectInputStream inStream = new ObjectInputStream(fileIn);
        final TypeData obj = (TypeData) inStream.readObject();
        inStream.close();
        fileIn.close();
        return obj;
    }
    
    @Test
    public void testExternalizable() throws IOException, ClassNotFoundException, EngineException {
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BooleanData.NULL)), BooleanData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BooleanData.TRUE)), BooleanData.TRUE);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BooleanData.FALSE)), BooleanData.FALSE);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.NULL)), IntData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.ZERO)), IntData.ZERO);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.valueOf(100))), IntData.valueOf(100));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.valueOf(300))), IntData.valueOf(300));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.valueOf(100000))), IntData.valueOf(100000));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, FloatData.valueOf(100f))), FloatData.valueOf(100f));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.NULL)), LongData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.ZERO)), LongData.ZERO);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.valueOf(100l))), LongData.valueOf(100l));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.valueOf(300l))), LongData.valueOf(300l));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.valueOf(100000l))), LongData.valueOf(100000l));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.valueOf(10000000000l))), LongData.valueOf(10000000000l));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DoubleData.valueOf(100d))), DoubleData.valueOf(100d));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DecimalData.valueOf(BigDecimal.valueOf(100l)))), DecimalData.valueOf(BigDecimal.valueOf(100l)));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DecimalData.valueOf(new BigDecimal("0")))), DecimalData.valueOf(new BigDecimal("0")));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DecimalData.valueOf(new BigDecimal("0.13828")))), DecimalData.valueOf(new BigDecimal("0.13828")));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DecimalData.valueOf(new BigDecimal("13828939292")))), DecimalData.valueOf(new BigDecimal("13828939292")));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, 
                DecimalData.valueOf(BigDecimal.ONE.divide(BigDecimal.valueOf(3l), new MathContext(32, RoundingMode.HALF_UP))))), 
                DecimalData.valueOf(BigDecimal.ONE.divide(BigDecimal.valueOf(3l), new MathContext(32, RoundingMode.HALF_UP)))
                );
        assertEquals(deserializeExternalizable(serializeExternalizable(location, 
                DecimalData.valueOf(new BigDecimal(new BigInteger("37107287533902102798797998220837590246510135740250"), 0)))), 
                DecimalData.valueOf(new BigDecimal(new BigInteger("37107287533902102798797998220837590246510135740250"), 0))
                );
        assertEquals(deserializeExternalizable(serializeExternalizable(location, StringData.NULL)), StringData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, StringData.EMPTY)), StringData.EMPTY);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, StringData.valueOf("1"))), StringData.valueOf("1"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, 
                    StringData.valueOf("coolaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))), 
                    StringData.valueOf("coolaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BinaryData.NULL)), BinaryData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BinaryData.EMPTY)), BinaryData.EMPTY);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BinaryData.valueOf("cool"))), BinaryData.valueOf("cool"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, 
                BinaryData.valueOf("coolaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))), 
                BinaryData.valueOf("coolaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DateData.valueOf("2017-10-15"))), DateData.valueOf("2017-10-15"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, TimeData.valueOf("11:20:31.281"))), TimeData.valueOf("11:20:31.281"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, TimestampData.valueOf("2017-10-15 11:20:31.281"))), TimestampData.valueOf("2017-10-15 11:20:31.281"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, CalendarTimeData.valueOf("11:20:31.281-0800"))), CalendarTimeData.valueOf("11:20:31.281-0800"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, CalendarTimestampData.valueOf("2017-10-15 11:20:31.281-0800"))), CalendarTimestampData.valueOf("2017-10-15 11:20:31.281-0800"));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, InstantData.valueOf("2017-10-15 11:20:31.281"))), InstantData.valueOf("2017-10-15 11:20:31.281"));
        
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BooleanData.NULL)), BooleanData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, IntData.NULL)), IntData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, FloatData.NULL)), FloatData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, LongData.NULL)), LongData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DoubleData.NULL)), DoubleData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DecimalData.NULL)), DecimalData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, StringData.NULL)), StringData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, BinaryData.NULL)), BinaryData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, DateData.NULL)), DateData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, TimeData.NULL)), TimeData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, TimestampData.NULL)), TimestampData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, CalendarTimeData.NULL)), CalendarTimeData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, CalendarTimestampData.NULL)), CalendarTimestampData.NULL);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, InstantData.NULL)), InstantData.NULL);
        
        final ListData list = new MutableListData().add(DoubleData.valueOf(1d))
                                                   .add(DoubleData.valueOf(0d))
                                                   .add(DoubleData.valueOf(2d))
                                                   .add(DoubleData.valueOf(0d));
        final MapData map = new MutableMapData().put(StringData.valueOf("a"), DoubleData.valueOf(1d))
                                                  .put(StringData.valueOf("a"), DoubleData.valueOf(0d))
                                                  .put(StringData.valueOf("a"), DoubleData.valueOf(2d))
                                                  .put(StringData.valueOf("a"), DoubleData.valueOf(0d));
        assertEquals(deserializeExternalizable(serializeExternalizable(location, list)), list);
        assertEquals(deserializeExternalizable(serializeExternalizable(location, list.toSetData())), list.toSetData());
        assertEquals(deserializeExternalizable(serializeExternalizable(location, list.toDenseVectorData())), list.toDenseVectorData());
        assertEquals(deserializeExternalizable(serializeExternalizable(location, list.toSparseVectorData())), list.toSparseVectorData());
        assertEquals(deserializeExternalizable(serializeExternalizable(location, map)), map);
        
    }
}
