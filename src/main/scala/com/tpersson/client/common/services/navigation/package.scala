package com.tpersson.client.common.services

import de.saxsys.mvvmfx.FxmlView

package object navigation {
  type PageViewType = Class[_ <: FxmlView[_]]
}
