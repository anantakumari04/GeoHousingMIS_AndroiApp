1. Introduction
GeoHousing MIS is a mobile-based decision support system built for rural housing development and land suitability analysis. The application integrates GIS concepts with mobile technologies to enable planners, administrators, and citizens to assess land plots based on multiple environmental and infrastructural parameters.

The app facilitates user-driven input, preference customization, and data visualization, ensuring a practical, field-friendly tool for rural development initiatives.

🏗️ 2. Core Modules and Features
A. HomeFragment – Land Entry Overview
Purpose: Displays a dynamic list of land entries added by the user.

Features:
Shows key details of each land plot: soil type, elevation, infrastructure access, population density, etc.

Uses a RecyclerView for efficient scrolling and display.

Clicking on an entry navigates to the AnalysisFragment for in-depth review.

B. SettingsFragment – Land Input & Preferences
Purpose: Enables entry of new land data and custom weight preferences for suitability analysis.

Features:

Input fields for land parameters like soil quality, elevation, road access, utilities, risk zones, etc.

Custom preference sliders or input fields for factor weightage (e.g., give more importance to infrastructure over elevation).

Uses SharedPreferences to save preferences persistently.

C. AnalysisFragment – Suitability Score & Visualization
Purpose: Analyzes and visualizes the suitability of selected land entries.

Features:

Calculates a suitability score based on user-entered data and preferences.

Displays a PieChart (via MPAndroidChart) showing the contribution of each factor.

Provides score breakdown and insights to aid decision-making.

🧠 3. Functional Workflow
User enters new land details in the SettingsFragment.

Data is saved locally and shown in the HomeFragment list.

User clicks a land entry to trigger suitability analysis.

AnalysisFragment retrieves preferences (from SharedPreferences) and calculates a weighted score.

Results are visualized using a PieChart with clear labels and dynamic UI.

📊 4. Technical Details
Component	Description
Language	Kotlin
UI Elements	Fragments, RecyclerView, EditText, Buttons, PieChart, ScrollView
Persistence	SharedPreferences (for analysis weight preferences), Kotlin Data Classes (for land entries, stored in memory or optional local DB)
Charting Library	MPAndroidChart for Pie Chart visualization
Navigation	Fragment Navigation Component or manual FragmentManager transactions
Data Transfer	Serializable/Parcelable Data Classes for passing data between fragments


📌 5. Advantages
Personalized analysis using saved preferences

User-friendly interface with easy data entry

Effective visualization using charts for better understanding

Offline-first approach for remote/rural use cases

🚀 7. Future Enhancements
Cloud Integration: Store land data and preferences using Firebase Firestore.

Map Integration: Use Google Maps API to pinpoint land location.

AI-Based Scoring: Suggest optimal plots based on demographic and environmental trends.

Reporting & Export: Generate downloadable PDF reports for land analysis.

📚 8. Conclusion
GeoHousing MIS is a lightweight, impactful mobile application that bridges technology with rural development planning. By empowering users to input, analyze, and visualize land data, the app helps make informed, data-driven decisions for sustainable housing development.

