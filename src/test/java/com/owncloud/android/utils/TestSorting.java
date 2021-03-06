package com.owncloud.android.utils;

import com.owncloud.android.datamodel.OCFile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import third_parties.daveKoeller.AlphanumComparator;

import static org.junit.Assert.assertTrue;

/*
 * Nextcloud Android client application
 *
 * @author Tobias Kaminsky
 * Copyright (C) 2017 Tobias Kaminsky
 * Copyright (C) 2017 Nextcloud GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 Tests used from 
 https://github.com/nextcloud/server/blob/9a4253ef7c34f9dc71a6a9f7828a10df769f0c32/tests/lib/NaturalSortTest.php
 at 2017-11-21 to stay in sync with server.
 Added first test with special chars
 */

public class TestSorting {

    @Test
    public void testSpecialChars() {
        String[] sortedArray = {"[Test] Folder", "01 - January", "11 - November", "Ôle",
                "Test 1", "Test 01", "Test 04", "Üüü",
                "z.[Test], z. Test"};

        assertTrue(sortAndTest(Arrays.asList(sortedArray)));
    }

    @Test
    public void testDifferentCasing() {
        String[] sortedArray = {"aaa", "AAA", "bbb", "BBB"};

        assertTrue(sortAndTest(Arrays.asList(sortedArray)));
    }

    @Test
    public void testLeadingZeros() {
        String[] sortedArray = {"2012-09-15 22.50.37.jpg", "2012-Card.jpg", "1584164_460s_v1.jpg", "08082008.jpg",
                "02122011150.jpg", "03122011151.jpg", "9999999999999999999999999999991.jpg",
                "9999999999999999999999999999992.jpg", "T 0 abc", "T 00 abc", "T 000 abc", "T 1 abc", "T 01 abc",
                "T 001 abc", "T 2 abc", "T 02 abc", "T 3 abc", "T 03 abc"};

        assertTrue(sortAndTest(Arrays.asList(sortedArray)));
    }

    @Test
    public void testTrailingDigits() {
        String[] sortedArray = {"A", "T", "T 01", "T 2", "T 003", "Zeros", "Zeros 2"};

        assertTrue(sortAndTest(Arrays.asList(sortedArray)));
    }

    @Test
    public void testOCFilesWithFolderFirst() {
        List<OCFile> sortedArray = new ArrayList<>();
        sortedArray.add(new OCFile("/ah.txt").setFolder());
        sortedArray.add(new OCFile("/Äh.txt").setFolder());
        sortedArray.add(new OCFile("/oh.txt").setFolder());
        sortedArray.add(new OCFile("/öh.txt").setFolder());
        sortedArray.add(new OCFile("/üh.txt").setFolder());
        sortedArray.add(new OCFile("/Üh.txt").setFolder());
        sortedArray.add(new OCFile("/äh.txt"));
        sortedArray.add(new OCFile("/Öh.txt"));
        sortedArray.add(new OCFile("/uh.txt"));
        sortedArray.add(new OCFile("/Üh 2.txt"));

        assertTrue(sortAndTest(sortedArray));
    }

    /**
     * uses OCFile.compareTo() instead of custom comparator
     */
    @Test
    public void testOCFiles() {
        List<OCFile> sortedArray = new ArrayList<>();
        sortedArray.add(new OCFile("/ah.txt").setFolder());
        sortedArray.add(new OCFile("/Äh.txt").setFolder());
        sortedArray.add(new OCFile("/oh.txt").setFolder());
        sortedArray.add(new OCFile("/öh.txt").setFolder());
        sortedArray.add(new OCFile("/üh.txt").setFolder());
        sortedArray.add(new OCFile("/Üh.txt").setFolder());
        sortedArray.add(new OCFile("/äh.txt"));
        sortedArray.add(new OCFile("/Öh.txt"));
        sortedArray.add(new OCFile("/uh.txt"));
        sortedArray.add(new OCFile("/Üh 2.txt"));

        List unsortedList = shuffle(sortedArray);
        Collections.sort(unsortedList);

        assertTrue(test(sortedArray, unsortedList));
    }

    @Test
    public void testSortCloudFiles() {
        List<OCFile> array = new ArrayList<>();
        array.add(new OCFile("/Joplin/0ed1778f8f88414286c13b2cbff8664e.md"));
        array.add(new OCFile("/Joplin/0edf70ab1b722172088257b3d73d6c46.md"));
        array.add(new OCFile("/Joplin/0fb063d4d18128fdf464878d18439ca3.md"));
        array.add(new OCFile("/Joplin/10e27391096748b187392f913da048f8.md"));
        array.add(new OCFile("/Joplin/4093463de91947b990fccff4a1d13d8f.md"));
        array.add(new OCFile("/Joplin/41742897324842849667274b2d1a0bb6.md"));
        array.add(new OCFile("/Joplin/41890bd1b11a44f7a6b226120ee1efbc.md"));
        array.add(new OCFile("/Joplin/41bd23f62ebb4d0b64c5f78b5ad13133.md"));
        array.add(new OCFile("/Joplin/4292ef3223334677bed0ad0268e5bd4b.md"));
        array.add(new OCFile("/Joplin/4307355783f4108aad5eba4d1b81ff91.md"));
        array.add(new OCFile("/Joplin/448d96a6c76b48b68030c8c11339088d.md"));
        array.add(new OCFile("/Joplin/4969e02c5c504899b17a355e61989bed.md"));
        array.add(new OCFile("/Joplin/49bfe05d968c3c0612ddceb2250b3a18.md"));
        array.add(new OCFile("/Joplin/701409a07ee4464fb82d9bc0c451f204.md"));
        array.add(new OCFile("/Joplin/70ba2272c85353a0216577ec76d0d343.md"));
        array.add(new OCFile("/Joplin/71563833027a49dcaa86208a3f621402.md"));
        array.add(new OCFile("/Joplin/7172f37973434fe3aeb6ef96bb48d968.md"));
        array.add(new OCFile("/Joplin/72068539d77c6e7b3271b653422d6e76.md"));
        array.add(new OCFile("/Joplin/73098c81e1ae4831b3a90ffda82ddcec.md"));
        array.add(new OCFile("/Joplin/730c09e169bc4289af163a34d7fe2553.md"));
        array.add(new OCFile("/Joplin/736be455ce8aff18694649001a74722a.md"));
        array.add(new OCFile("/Joplin/749c837930174e828620a99a9e7ad7bc.md"));
        array.add(new OCFile("/Joplin/8a2d6a6461eb4979bb049837a8710a3d.md"));
        array.add(new OCFile("/Joplin/8b7ff9081f5944399e1fc83c6191a09f.md"));
        array.add(new OCFile("/Joplin/8c45ac421149a21f32edab06f0955839.md"));
        array.add(new OCFile("/Joplin/8cd855a41cca4cbd8a0c20e79c4f2f9e.md"));
        array.add(new OCFile("/Joplin/8f062be696e3488ca12a9d3f3f6aa10a.md"));
        array.add(new OCFile("/Joplin/8f74a571a11b4709bb1cdf75b4a51a8e.md"));
        array.add(new OCFile("/Joplin/90f61d36f4dd4ef79525b7b0812213e2.md"));
        array.add(new OCFile("/Joplin/9846fdafaffa718ebcfa19474e8be251.md"));
        array.add(new OCFile("/Joplin/a441cb730c22451aa4352129231fe898.md"));
        array.add(new OCFile("/Joplin/a551ce1ae06e6f05669a0cadc4eb2f7b.md"));
        array.add(new OCFile("/Joplin/a590cd1671b648c7a3bae0d6fbb7da81.md"));

        FileSortOrderByName fileSortOrderByName = new FileSortOrderByName("test", true);
        fileSortOrderByName.sortCloudFiles(array);
    }

    private List<Comparable> shuffle(List<? extends Comparable> files) {
        List<Comparable> shuffled = new ArrayList<>();
        shuffled.addAll(files);

        Collections.shuffle(shuffled);

        return shuffled;
    }

    private boolean sortAndTest(List<? extends Comparable> sortedList) {
        return test(sortedList, sort(sortedList));
    }

    private List<Comparable> sort(List<? extends Comparable> sortedList) {
        List unsortedList = shuffle(sortedList);

        if (sortedList.get(0) instanceof OCFile) {
            Collections.sort(unsortedList, (Comparator<OCFile>) (o1, o2) -> {
                if (o1.isFolder() && o2.isFolder()) {
                    return new AlphanumComparator().compare(o1, o2);
                } else if (o1.isFolder()) {
                    return -1;
                } else if (o2.isFolder()) {
                    return 1;
                }
                return new AlphanumComparator().compare(o1, o2);
            });
        } else {
            Collections.sort(unsortedList, new AlphanumComparator<>());
        }

        return unsortedList;
    }

    private boolean test(List<? extends Comparable> target, List<? extends Comparable> actual) {

        for (int i = 0; i < target.size(); i++) {
            int compare;

            if (target.get(i) instanceof OCFile) {
                String sortedName = ((OCFile) target.get(i)).getFileName();
                String unsortedName = ((OCFile) actual.get(i)).getFileName();
                compare = sortedName.compareTo(unsortedName);
            } else {
                compare = target.get(i).compareTo(actual.get(i));
            }

            if (compare != 0) {

                System.out.println(" target: \n" + target.toString());
                System.out.println(" actual: \n" + actual.toString());

                return false;
            }
        }

        return true;
    }
}
