import com.github.UniHelper.model.timetable.DefaultTimetableModel;
import com.github.UniHelper.model.timetable.TimetableModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class DefaultTimetableModelTest {
    private TimetableModel timetableModel;

    @BeforeEach
    void initialize() {
        timetableModel = new DefaultTimetableModel();
    }

    @Test
    void accessorsTest(){
        //Given
        BufferedImage input1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        BufferedImage input2 = new BufferedImage(2, 2, BufferedImage.TYPE_3BYTE_BGR);
        BufferedImage input3 = null;

        //When
        timetableModel.setTimetable(input1);
        BufferedImage output1 = timetableModel.getTimetable();
        timetableModel.setTimetable(input2);
        BufferedImage output2 = timetableModel.getTimetable();
        timetableModel.setTimetable(input3);
        BufferedImage output3 = timetableModel.getTimetable();

        //Then
        Assertions.assertTrue(compareImages(input1, output1));
        Assertions.assertTrue(compareImages(input2, output2));
        Assertions.assertTrue(compareImages(input3, output3));
    }

    @Test
    void deleteTimetableTest() {
        //Given
        BufferedImage input1 = null;
        BufferedImage input2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        //When
        timetableModel.setTimetable(input1);
        timetableModel.deleteTimetable();
        BufferedImage output1 = timetableModel.getTimetable();

        timetableModel.setTimetable(input2);
        timetableModel.deleteTimetable();
        BufferedImage output2 = timetableModel.getTimetable();

        //Then
        Assertions.assertNull(output1);
        Assertions.assertNull(output2);
    }

    private static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        if(imgA == null || imgB == null)
            return imgA == imgB;

        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return false;
        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
