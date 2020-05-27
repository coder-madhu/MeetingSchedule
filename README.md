# Conference room scheduling code

* **Step1:** Load given list of conference rooms with capacity and available times into the array 

* **Step2:** Read input for the required meeting from command prompt

* **Step3:** Parse list of conference rooms information/[ Makeing list of available rooms].
  * Get room capacity, start time(converting time to int), end time(converting time to int), meeting room id for each slot
  * Build available slot for each available time for any conference room and add it to a list 
  * Implemented comparator to sort available slots with start time as least and end time as max when start time is same

* **Step4:** Parse the input and get the information

* **Step5:** Compare the input data against the available sorted slots information
  * Order: capacity -> start time and end time
  * Check  if it can fit in single room other wise try to fit in multi room based on the flag

* **Step6:** If single room found
  * Check for the closest meeting room by distance and display
  * Other wise print multi rooms that are available for the given input
  * Even there is no data also covered as - No single or multi meeting room found
