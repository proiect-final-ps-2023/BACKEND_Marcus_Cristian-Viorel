package com.hotel.hotelreservationsystem.export;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BookingXMLExporter {

    public static void export(List<BookingDTO> bookings, String filePath) throws JAXBException, IOException {
        BookingListWrapper wrapper = new BookingListWrapper(bookings);

        JAXBContext context = JAXBContext.newInstance(BookingListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file, false);
        marshaller.marshal(wrapper, outputStream);
        outputStream.close();
    }
}
