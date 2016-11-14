package main.java.com.glt.webtest.SE;

import java.util.ArrayList;
import java.util.List;

public class Calendar {

	/**
	 * @param year
	 *            ָ�����
	 * @param month
	 *            ָ���·�
	 * @param count
	 *            �����ָ�����¿�ʼ��count���·ݵ�����
	 * @param row
	 *            ÿ��������·�����
	 * @return
	 * @throws Exception
	 */
	public List<String> formatCalendar(int year, int month, int count, int row)
			throws Exception {
		List<String> str = new ArrayList<String>();
		// Begin �����Ҫ�󲹳����
		// ��������������˽�з������Ա�֤��������÷��

		double dl = ((double) count) / row;
		for (int j = 1; j <= (int) Math.ceil(dl); j++) {// ÿ����
			int _row = 0;
			if (j * row > count) {
				_row = count - (j - 1) * row;
			} else {
				_row = row;
			}

			String head = "";
			for (int r = 0; r < _row; r++) {
				head += "  ��  һ  ��  ��  ��  ��  ��";
			}
			str.add(head);// ͷ

			for (int i = 1; i <= _row; i++) {// ÿ��
				int _month = month;
				int _year = year;
				_month = month + ((j - 1) * _row + i - 1);
				if (_month > 12) {
					_month = _month - 12;
					_year = year + 1;
				}

				for (int n = 1; n <= 6; n++) {// ÿ��

					int fnml = getFirstNumByMonthLine(_year, _month, n);
					Integer[] iArray = new Integer[] { fnml, fnml + 1,
							fnml + 2, fnml + 3, fnml + 4, fnml + 5, fnml + 6 };
					int j6n = (j - 1) * 7 + n;// ������
					if (str.size() >= j6n + 1 && str.get(j6n) != null) {
						str.set(j6n,
								str.get(j6n)
										+ formatLine(iArray, _month, _year));
					} else {
						str.add(formatLine(iArray, _month, _year));
					}
				}
			}
		}

		// End
		return str;
	}

	private static int[] maxMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	private static int[] maxMonth_runNian = { 0, 31, 29, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	private String formatLine(Object[] iArray, int month, int year) {
		// System.out.println(Arrays.toString(iArray));
		for (int i = 0; i < iArray.length; i++) {
			if (isRunNian(year)) {
				if ((Integer) iArray[i] < 1
						|| (Integer) iArray[i] > Calendar.maxMonth_runNian[month]) {
					iArray[i] = null;
				}
			} else {
				if ((Integer) iArray[i] < 1
						|| (Integer) iArray[i] > Calendar.maxMonth[month]) {
					iArray[i] = null;
				}
			}
		}
		String s = "  %1$2d  %2$2d  %3$2d  %4$2d  %5$2d  %6$2d  %7$2d";
		return String.format(s, iArray).replace("null", "  ");
	}

	/**
	 * �õ�ĳ��ĳ��ĳ�еĵ�һ��������
	 * 
	 * @param year
	 * @param month
	 * @param line
	 * @return
	 */
	private int getFirstNumByMonthLine(int year, int month, int line) {
		int offset = getDayOfWeek(year, month, 1);
		if (getDayOfWeek(year, month, 1) == 7) {
			offset = 0;
		}
		return (line - 1) * 7 + 1 - offset;
	}

	/**
	 * �õ���������1-7
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private int getDayOfWeek(int year, int month, int day) {
		if (month < 1 || month > 12 || day < 1 || day > 31) {
			System.out.println(month + "--" + day);
			throw new IllegalArgumentException("�·���λ���������β��ں���ĺ���ķ�Χ�ڡ�");
		}
		int d = day + 1;
		int m = month;
		int y = year;
		if (month == 1 || month == 2) {
			m = month + 12;
			y = year - 1;
		}
		int w = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		if (w == 0) {
			w = 7;
		}
		return w;

	}

	/**
	 * �ǲ�������
	 * @param year
	 * @return
	 */
	private boolean isRunNian(int year) {
		if (year % 4 != 0) {
			return false;
		} else {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

	public static void main(String[] args) {
		try {
			int year = Integer.parseInt(args[0]);
			int month = Integer.parseInt(args[1]);
			int count = Integer.parseInt(args[2]);
			int row = Integer.parseInt(args[3]);

			// int year = 2011;
			// int month = 12;
			// int count = 7;
			// int row = 3;

			List<String> result = new Calendar().formatCalendar(year, month,
					count, row);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
