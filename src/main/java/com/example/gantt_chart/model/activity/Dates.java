package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that represents whole date tag
 * <date>
 * <start>@date</start>
 * <end>@date</end>
 * </date>
 *
 * @date - is date in timestamp format
 */
public class Dates implements Convertible {
    private Date start;
    private Date end;

    /**
     * Constructs start and end date of activity. Receives dates as Date class
     *
     * @param start - start date of activity
     * @param end   - end date of activity
     * @throws DatesException
     */
    public Dates(@NotNull Date start, @NotNull Date end) throws DatesException {
        checkDateForNull(start);
        checkDateForNull(end);
        this.start = start;
        this.end = end;
        checkDateCorrection();
    }

    /**
     * Constructs start and end date of activity. Receives dates as long values(timestamps)
     *
     * @param timestampStart - start date of activity
     * @param timestampEnd   - end date of activity
     * @throws DatesException
     */
    public Dates(long timestampStart, long timestampEnd) throws DatesException {
        this(new Date(timestampStart), new Date(timestampEnd));
    }

    public Date getStart() {
        return start;
    }

    public void setStart(@NotNull Date start) throws DatesException {
        checkDateForNull(start);
        this.start = start;
        checkDateCorrection();
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(@NotNull Date end) throws DatesException {
        checkDateForNull(end);
        this.end = end;
        checkDateCorrection();
    }

    /**
     * Converts object to Json representation of its xml analogue
     *
     * <dates>
     *     <start>1559236223000</start>
     *     <end>1560384000000</end>
     * </dates>
     *          ||
     *          ||
     *         \  /
     *          \/
     *{
     *     "start-date" : "30/05/2019"
     *     "end-date" : "13/06/2019"
     *     "duration" : 14
     *}
     * @return json Element
     */
    public JsonElement toJson() {
        JsonObject result = new JsonObject();
        result.addProperty("start-date", new SimpleDateFormat("dd-MM-yyyy").format(start));
        result.addProperty("end-date", new SimpleDateFormat("dd-MM-yyyy").format(end));
        result.addProperty("duration", (int) (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000) + 1);
        return result;
    }

    /**
     * Checks if dates are correctly ordered
     *
     * @throws DatesException if end date < start date
     */
    private void checkDateCorrection() throws DatesException {
        if (start.compareTo(end) > 0) {
            throw new DatesException("The start date has to be earlier than the end date");
        }
    }

    /**
     * Checks dates for null
     *
     * @param date - date that has to be checked
     * @throws DatesException if date is null
     */
    private void checkDateForNull(Date date) throws DatesException {
        if (date == null) {
            throw new DatesException("Trying to set start|end date to null: start and end date is not @Nullable!");
        }
    }
}