package com.spamalot.chess.base.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.spamalot.chess.board.SquareUtil;

public class SquareUtilTest {
	private SquareUtil squ = SquareUtil.valueOf(1, 4);

	/** For testing exceptions. */
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetFile() {
		assertEquals(1, this.squ.getFile());
	}

	@Test
	public void testGetRank() {
		assertEquals(4, this.squ.getRank());
	}

	@Test
	public void testValueOfNotNull() {
		SquareUtil a = SquareUtil.valueOf(1, 2);
		org.junit.Assert.assertNotNull(a);
	}

	@Test
	public void testValueOfRange() {
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("File is -1.  Must be between 0 and 7 inclusive.");

		SquareUtil.valueOf(-1, 2);
	}

	@Test
	public void testValueOfOutOfRange() {
		try {
			SquareUtil.valueOf(1, 29);
			org.junit.Assert.fail("Should have gotten an exception.");
		} catch (IllegalArgumentException e) {
			// Expected.
		}

		try {
			SquareUtil.valueOf(1, -5);
			org.junit.Assert.fail("Should have gotten an exception.");
		} catch (IllegalArgumentException e) {
			// Expected.
		}

		try {
			SquareUtil.valueOf(10, 2);
			org.junit.Assert.fail("Should have gotten an exception.");
		} catch (IllegalArgumentException e) {
			// Expected.
		}

		try {
			SquareUtil.valueOf(-6, 2);
			fail("Should have gotten an exception.");
		} catch (IllegalArgumentException e) {
			// Expected.
		}
	}

	@Test
	public void testToString() {
		assertNotNull(SquareUtil.valueOf(4, 4).toString());
	}

	@Test
	public void testEquals() {
		assertEquals(SquareUtil.valueOf(4, 4), SquareUtil.valueOf(4, 4));
		assertNotEquals(SquareUtil.valueOf(4, 4), SquareUtil.valueOf(1, 2));

		assertNotEquals(SquareUtil.valueOf(4, 4), SquareUtil.valueOf(4, 2));
		assertNotEquals(SquareUtil.valueOf(4, 4), SquareUtil.valueOf(2, 4));

		assertNotEquals(SquareUtil.valueOf(4, 4), null);
		assertNotEquals(SquareUtil.valueOf(4, 4), "Hello, World!");
	}

	@Test
	public void testHash() {
		// Should be 64 different hash values.
		Set<Integer> x = new HashSet<>();
		for (int file = 0; file < 8; file++) {
			for (int rank = 0; rank < 8; rank++) {
				System.out.println(SquareUtil.valueOf(file, rank).hashCode());
				x.add(SquareUtil.valueOf(file, rank).hashCode());
			}
		}

		assertEquals(64, x.size());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(this.squ == SquareUtil.valueOf(1, 4));

		assertFalse(this.squ == SquareUtil.valueOf(4, 1));
	}
}
